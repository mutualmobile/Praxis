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
class HomeViewModel @Inject constructor() : BaseViewModel() {
  @Inject
  lateinit var jokeRepo: JokeRepo
  @Inject
  lateinit var rxApiService: RxApiService
  @Inject
  lateinit var schedulers: IRxSchedulers

  var dataLoading: MutableLiveData<Boolean> = MutableLiveData()
  var dataJokes: MutableLiveData<JokeListResponse> = MutableLiveData()

  fun loadDataCoroutine() {
    dataLoading.value = true
    viewModelScope.launch {
      val jokeListResult = jokeRepo.getFiveRandomJokes()
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
    addDisposable(rxApiService.getFiveRandomJokes()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .doFinally { dataLoading.value = false }
        .subscribe({ response ->
          dataJokes.value = response
        }, { Timber.e(it) })
    )
  }
}