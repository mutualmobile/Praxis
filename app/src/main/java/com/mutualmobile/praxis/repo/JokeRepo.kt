package com.mutualmobile.praxis.repo

import androidx.lifecycle.MutableLiveData
import com.mutualmobile.praxis.NetworkResult
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.services.CoroutineApiService
import com.mutualmobile.praxis.data.services.RxApiService
import com.mutualmobile.praxis.ui.base.BaseRepo
import com.mutualmobile.praxis.utils.IRxSchedulers
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class JokeRepo @Inject constructor(
    private val coroutineApiService: CoroutineApiService,
    private val rxApiService: RxApiService,
    private val schedulers: IRxSchedulers
) : BaseRepo() {

  var repoDataLoading: MutableLiveData<Boolean> = MutableLiveData()
  var repoDataJokes: MutableLiveData<JokeListResponse> = MutableLiveData()

  suspend fun getFiveRandomJokesCoroutine(): NetworkResult<JokeListResponse> {
    val response = coroutineApiService.getFiveRandomJokes()
    if (response.isSuccessful) {
      val data = response.body()
      if (data != null) {
        return NetworkResult.Success(data)
      }
    }
    return NetworkResult.Failure(response)
  }

  suspend fun loadDataCoroutine() {
    repoDataLoading.value = true
    val jokeListResult = getFiveRandomJokesCoroutine()
    repoDataLoading.value = false
    when (jokeListResult) {
      is NetworkResult.Success -> {
        repoDataJokes.value = jokeListResult.body
      }
      is NetworkResult.Failure -> {
        Timber.e("onError")
      }
    }
  }

  fun loadDataRx() {
    repoDataLoading.value = true
    addDisposable(getFiveRandomJokesRx()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .doFinally { repoDataLoading.value = false }
        .subscribe({ response ->
          repoDataJokes.value = response
        }, { Timber.e(it) })
    )
  }

  private fun getFiveRandomJokesRx(): Single<JokeListResponse> {
    return rxApiService.getFiveRandomJokes()
  }
}