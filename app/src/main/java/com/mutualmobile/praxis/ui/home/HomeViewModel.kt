package com.mutualmobile.praxis.ui.home

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import com.mutualmobile.praxis.data.services.ApiService
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.ui.base.navigator.Navigator
import com.mutualmobile.praxis.ui.joke.ShowJokeActivity
import com.mutualmobile.praxis.utils.IRxSchedulers
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor() : BaseViewModel() {
  @Inject
  lateinit var service: ApiService
  @Inject
  lateinit var schedulers: IRxSchedulers
  @Inject
  lateinit var navigator: Navigator

  var dataLoading: MutableLiveData<Boolean> = MutableLiveData()

  fun loadData() {
    dataLoading.postValue(true)
    addDisposable(service.getFiveRandomJokes()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.main())
        .doFinally { dataLoading.postValue(false) }
        .subscribe({ response ->
          //TODO: If list of joke, send it to JokeListActivity
          val bundle = Bundle()
          bundle.putParcelableArrayList(ShowJokeActivity.JOKE_LIST_INTENT, response.value)
          navigator.startActivityWithData(ShowJokeActivity::class.java, bundle)
        }, { Timber.e(it) }))
  }
}