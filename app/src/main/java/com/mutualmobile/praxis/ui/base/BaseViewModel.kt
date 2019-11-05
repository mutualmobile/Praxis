package com.mutualmobile.praxis.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

  private var compositeDisposable: CompositeDisposable? = null

  override fun onCleared() {
    super.onCleared()

    if (compositeDisposable != null) {
      compositeDisposable!!.dispose()
      compositeDisposable!!.clear()
      compositeDisposable = null
    }
  }

  protected fun addDisposable(disposable: Disposable) {
    if (compositeDisposable == null) {
      compositeDisposable = CompositeDisposable()
    }
    compositeDisposable!!.add(disposable)
  }
}
