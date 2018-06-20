package com.mutualmobile.praxis.ui.home

import android.app.Dialog
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mutualmobile.praxis.R
import dagger.android.DaggerDialogFragment

class AboutFragment : DaggerDialogFragment(), LifecycleOwner {

  companion object {
    fun newInstance(): AboutFragment {
      val fragment = AboutFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
  override fun getLifecycle(): Lifecycle {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_about, container, false)
  }

}