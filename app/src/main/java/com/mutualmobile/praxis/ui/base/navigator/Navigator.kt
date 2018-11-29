package com.mutualmobile.praxis.ui.base.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

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

  fun startActivityWithDataAndAnimation(
    activityClass: Class<out Activity>,
    bundle: Bundle,
    inAnimation: Int,
    outAnimation: Int
  ) {
    val activity = getActivity()
    val intent = Intent(activity, activityClass)
    intent.putExtras(bundle)
    activity.startActivity(intent)
    getActivity().overridePendingTransition(inAnimation, outAnimation)
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

  fun finishActivityWithAnimation(
    inAnimation: Int,
    outAnimation: Int
  ) {
    getActivity().finish()
    getActivity().overridePendingTransition(inAnimation, outAnimation)
  }

  //Add more methods here... like add with animation
}