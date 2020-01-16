package com.mutualmobile.praxis.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseRepo {
  private var compositeDisposable: CompositeDisposable? = null

  fun onCleared() {
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