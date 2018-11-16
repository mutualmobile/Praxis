package com.mutualmobile.praxis.ui.base.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class Navigator {
  abstract fun getActivity(): AppCompatActivity

  fun startActivity(activityClass: Class<out Activity>) {
    val activity = getActivity()
    val intent = Intent(activity, activityClass)
    activity.startActivity(intent)
  }

  fun startActivityWithData(
    activityClass: Class<out Activity>,
    bundle: Bundle
  ) {
    val activity = getActivity()
    val intent = Intent(activity, activityClass)
    intent.putExtras(bundle)
    activity.startActivity(intent)
  }

  fun addFragment(
    containerId: Int,
    fragment: Fragment
  ) {
    getActivity().supportFragmentManager.beginTransaction()
        .add(containerId, fragment)
        .addToBackStack(fragment::class.java.simpleName)
        .commit()
  }

  fun replaceFragment(
    containerId: Int,
    fragment: Fragment
  ) {
    getActivity().supportFragmentManager.beginTransaction()
        .add(containerId, fragment)
        .addToBackStack(fragment::class.java.simpleName)
        .commit()
  }

  //Add more methods here... like add with animation
}