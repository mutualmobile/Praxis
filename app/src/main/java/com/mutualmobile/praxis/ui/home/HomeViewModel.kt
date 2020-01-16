package com.mutualmobile.praxis.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.services.RxApiService
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.repo.JokeRepo
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.utils.IRxSchedulers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor(
    private val jokeRepo: JokeRepo,
    private val schedulers: IRxSchedulers
) : BaseViewModel() {


  var dataLoading: MutableLiveData<Boolean> = MutableLiveData()
  var dataJokes: MutableLiveData<JokeListResponse> = MutableLiveData()

  fun loadDataCoroutine() {
    dataLoading.value = true
    viewModelScope.launch {
      val jokeListResult = jokeRepo.getFiveRandomJokesCoroutine()
      dataLoading.value = false
      when (jokeListResult) {
        is NetworkResult.Success -> {
          dataJokes.value = jokeListResult.body
        }
        is NetworkResult.Failure -> {
          Timber.e("onError")
        }
      }
    }
  }

  fun loadDataRx() {
    dataLoading.value = true
    addDisposable(jokeRepo.getFiveRandomJokesRx()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .doFinally { dataLoading.value = false }
        .subscribe({ response ->
          dataJokes.value = response
        }, { Timber.e(it) })
    )
  }
}