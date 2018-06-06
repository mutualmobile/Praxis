package com.mutualmobile.praxis.ui.base.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class Navigator {
  abstract fun getActivity(): Activity

  fun startActivityWithData(activityClass: Class<out AppCompatActivity>, bundle: Bundle) {
    val activity = getActivity()
    val intent = Intent(activity, activityClass)
    intent.putExtras(bundle)
    activity.startActivity(intent)
  }
}