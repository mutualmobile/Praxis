package com.mutualmobile.praxis.ui.kotlinsample

import com.mutualmobile.praxis.ui.base.MvvmView

interface KotlinView : MvvmView {
  fun showToast(string: String)
}
