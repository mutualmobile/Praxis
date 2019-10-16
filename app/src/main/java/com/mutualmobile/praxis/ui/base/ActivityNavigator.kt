package com.mutualmobile.praxis.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object ActivityNavigator{


  fun startActivity(activityClass: Class<out Activity>, activity: Activity) {
    val intent = Intent(activity, activityClass)
    activity.startActivity(intent)
  }

  fun startActivityWithData(
    activityClass: Class<out Activity>,
    bundle: Bundle, activity: AppCompatActivity
  ) {
    val intent = Intent(activity, activityClass)
    intent.putExtras(bundle)
    activity.startActivity(intent)
  }

  fun startActivityWithDataAndAnimation(
    activityClass: Class<out Activity>,
    bundle: Bundle,
    inAnimation: Int,
    outAnimation: Int,
    activity: AppCompatActivity
  ) {
    val intent = Intent(activity, activityClass)
    intent.putExtras(bundle)
    activity.startActivity(intent)
    activity.overridePendingTransition(inAnimation, outAnimation)
  }

  fun addFragment(
    containerId: Int,
    fragment: Fragment,
    activity: AppCompatActivity
  ) {
    activity.supportFragmentManager.beginTransaction()
        .add(containerId, fragment)
        .addToBackStack(fragment::class.java.simpleName)
        .commit()
  }

  fun replaceFragment(
    containerId: Int,
    fragment: Fragment,
    activity: AppCompatActivity
  ) {
    activity.supportFragmentManager.beginTransaction()
        .add(containerId, fragment)
        .addToBackStack(fragment::class.java.simpleName)
        .commit()
  }

  fun finishActivityWithAnimation(
    inAnimation: Int,
    outAnimation: Int,
    activity: AppCompatActivity
  ) {
    activity.finish()
    activity.overridePendingTransition(inAnimation, outAnimation)
  }

}