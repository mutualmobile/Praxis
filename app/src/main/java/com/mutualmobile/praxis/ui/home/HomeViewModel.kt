package com.mutualmobile.praxis.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.services.RxApiservice
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.repo.JokeRepo
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.ui.base.navigator.Navigator
import com.mutualmobile.praxis.utils.IRxSchedulers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor() : BaseViewModel() {
  @Inject
  lateinit var jokeRepo: JokeRepo
  @Inject
  lateinit var rxApiService: RxApiservice
  @Inject
  lateinit var navigator: Navigator
  @Inject
  lateinit var schedulers: IRxSchedulers

  var dataLoading: MutableLiveData<Boolean> = MutableLiveData()
  var dataJokes: MutableLiveData<com.mutualmobile.praxis.data.model.JokeListResponse> = MutableLiveData()

  fun loadDataCoroutine() {
    dataLoading.postValue(true)
    viewModelScope.launch {
      val jokeListResult = jokeRepo.getFiveRandomJokes()
      dataLoading.postValue(false)
      when (jokeListResult) {
        is NetworkResult.Success -> {
          dataJokes.postValue(jokeListResult.body)
        }
        is NetworkResult.Failure -> {
          Timber.e("onError")
        }
      }
    }
  }

  fun loadDataRx() {
    dataLoading.postValue(true)
    addDisposable(rxApiService.getFiveRandomJokes()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .doFinally { dataLoading.postValue(false) }
        .subscribe({ response ->
          dataJokes.postValue(response)
        }, { Timber.e(it) })
    )
  }
}