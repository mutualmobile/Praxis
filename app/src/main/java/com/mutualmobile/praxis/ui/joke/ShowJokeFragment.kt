package com.mutualmobile.praxis.ui.joke

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mutualmobile.praxis.R
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_show_joke.view.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ShowJokeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ShowJokeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowJokeFragment : DaggerFragment(), LifecycleOwner {

  @Inject lateinit var showJokeViewModel: ShowJokeViewModel
  @Inject lateinit var rxP: RxPermissions

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_show_joke, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("SCOPECHECK Fragment ::" + showJokeViewModel)
    Timber.d("SCOPECHECK Fragment RX ::" + rxP)
    showJokeViewModel.joke.observe(this, Observer { joke ->
      view.joke_view.text = joke
    })
    showJokeViewModel.loadData()
  }
}
