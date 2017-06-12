package com.mutualmobile.praxis.ui.base

import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseViewModel<T : MvvmView> : BaseObservable(), MvvmViewModel<T> {

  private var compositeDisposable: CompositeDisposable? = null
  var mvvmView: T? = null
    private set

  override fun attachView(view: T) {
    this.mvvmView = view
  }

  override fun detachView() {
    mvvmView = null
  }

  val isViewAttached: Boolean
    get() = mvvmView != null

  fun checkViewAttached() {
    if (!isViewAttached) throw MvpViewNotAttachedException()
  }

  fun unsubscribeFromDataStore() {
    Timber.d("unsubscribeFromDataStore(): ")
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

  class MvpViewNotAttachedException : RuntimeException(
      "Please call Presenter.attachView(CategoryMvpView) before" + " requesting data to the Presenter")
}