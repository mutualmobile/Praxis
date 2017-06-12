package com.mutualmobile.praxis.ui.base

interface MvvmViewModel<V : MvvmView> {
  fun attachView(view: V)

  fun detachView()
}
