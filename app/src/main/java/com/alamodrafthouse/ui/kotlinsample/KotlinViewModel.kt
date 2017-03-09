package com.alamodrafthouse.ui.kotlinsample

import com.alamodrafthouse.ui.base.BaseViewModel
import javax.inject.Inject


class KotlinViewModel
@Inject
constructor() : BaseViewModel<KotlinView>() {
  fun loadData() {
    mvvmView?.showToast()
  }
}


