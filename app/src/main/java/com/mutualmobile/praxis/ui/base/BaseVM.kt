package com.mutualmobile.praxis.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

abstract class BaseVM : ViewModel(), LifecycleObserver {

  override fun onCleared() {
    super.onCleared()
  }
}
