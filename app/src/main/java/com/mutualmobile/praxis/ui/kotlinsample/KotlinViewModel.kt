package com.mutualmobile.praxis.ui.kotlinsample

import com.mutualmobile.praxis.data.services.ApiService
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.utils.IRxSchedulers
import timber.log.Timber
import javax.inject.Inject


class KotlinViewModel @Inject constructor() : BaseViewModel<KotlinView>() {

  @Inject lateinit var service: ApiService
  @Inject lateinit var schedulers: IRxSchedulers

  var joke: String = ""

  fun loadData() {
    addDisposable(service.getRandomJoke()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .subscribe({ response ->
          joke = response.value.joke
          notifyChange()

          mvvmView?.showToast(response.type)
        }, { Timber.e(it) }))
  }
}