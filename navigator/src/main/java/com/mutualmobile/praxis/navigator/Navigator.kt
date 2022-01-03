package com.mutualmobile.praxis.navigator

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class Navigator(private val context: Context) {
  fun startScreen(apply: Intent) {
    context.startActivity(apply)
  }

  fun showFragmentDialog(newInstance: DialogFragment, tag: String) {
    newInstance.show((context as FragmentActivity).supportFragmentManager, tag)
  }
}