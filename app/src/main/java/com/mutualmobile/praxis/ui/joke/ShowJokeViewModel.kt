package com.mutualmobile.praxis.ui.joke

import android.arch.lifecycle.MutableLiveData
import com.mutualmobile.praxis.data.services.ApiService
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.utils.IRxSchedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ShowJokeViewModel @Inject constructor() : BaseViewModel() {

  @Inject lateinit var service: ApiService
  @Inject lateinit var schedulers: IRxSchedulers

  var joke: MutableLiveData<String> = MutableLiveData()

  fun loadData() {
    addDisposable(service.getRandomJoke()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .subscribe({ response ->
          joke.postValue(response.value.joke)
        }, { Timber.e(it) }))
  }
}