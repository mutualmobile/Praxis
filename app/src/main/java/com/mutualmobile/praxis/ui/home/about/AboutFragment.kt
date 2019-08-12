package com.mutualmobile.praxis.ui.home.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.FragmentAboutBinding
import dagger.android.support.DaggerAppCompatDialogFragment

class AboutFragment : DaggerAppCompatDialogFragment(), LifecycleOwner {

  companion object {
    fun newInstance(): AboutFragment {
      val fragment = AboutFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }

  private lateinit var binding: FragmentAboutBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
    binding.mutualMobileWebLink.movementMethod = LinkMovementMethod.getInstance()
    return binding.root
  }

}