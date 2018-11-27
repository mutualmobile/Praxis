package com.mutualmobile.praxis.ui.base.navigator

import androidx.appcompat.app.AppCompatActivity

class ActivityNavigator constructor(private val activity: AppCompatActivity) : Navigator() {

  override fun getActivity(): AppCompatActivity = activity

}