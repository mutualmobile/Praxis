package com.mutualmobile.praxis.ui.profile

import android.app.Activity
import androidx.lifecycle.Observer
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityProfileBinding
import com.mutualmobile.praxis.ui.base.BaseActivity
import timber.log.Timber

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

  override fun getViewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java

  override fun layoutId(): Int {
    return R.layout.activity_profile
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    loadProfileData()

    binding.apply {
      this.viewModel = viewModel
      setLifecycleOwner(this@ProfileActivity)
    }

    viewModel.userLiveData.observe(this, Observer { user ->
      user?.let {
        Timber.i("Live data object : ${user.name} , ${user.email}")
      }
    })

    viewModel.nameData.observe(this, Observer { name -> Timber.i("Live data : $name") })

    binding.btnSave.setOnClickListener {
      hideKeyboard()
      viewModel.saveData()
      Snackbar.make(binding.btnSave, "Data Saved Successfully", Snackbar.LENGTH_LONG)
          .show()
    }

  }

  private fun loadProfileData() {
    viewModel.loadData()
  }

  //Todo Move to utils class
  private fun hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
      view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
  }
}
