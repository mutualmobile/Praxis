package com.mutualmobile.praxis.ui.kotlinsample

import android.arch.lifecycle.MutableLiveData
import com.mutualmobile.praxis.data.services.ApiService
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.utils.IRxSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject


class KotlinViewModel @Inject constructor() : BaseViewModel() {

  @Inject lateinit var service: ApiService
  @Inject lateinit var schedulers: IRxSchedulers

  var joke: MutableLiveData<String> = MutableLiveData()

  fun loadData() {
    addDisposable(service.getRandomJoke()
        .subscribeOn(schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response ->
          joke.postValue(response.value.joke)
        }, { Timber.e(it) }))
  }
}