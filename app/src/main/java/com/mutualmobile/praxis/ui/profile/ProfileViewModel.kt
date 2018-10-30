package com.mutualmobile.praxis.ui.profile

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import android.databinding.InverseMethod
import com.mutualmobile.praxis.data.model.User
import com.mutualmobile.praxis.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : BaseViewModel() {
  @Inject lateinit var preferences: SharedPreferences

  var userLiveData = MutableLiveData<User>()
  var nameData = MutableLiveData<String>()

  init {
    userLiveData.value = User()
    nameData.value = ""
  }

  fun loadData() {
    val user = User(
        preferences.getString("name", "")!!, preferences.getString("email", "")!!
    )
    userLiveData.value = user
    nameData.value = user.name
  }

  fun saveData() {
    preferences.edit()
        .putString("name", getName())
        .putString("email", getEmail())
        .apply()
  }

  private fun getName(): String {
    return userLiveData.value?.name.toString()
  }

  private fun getEmail(): String {
    return userLiveData.value?.email.toString()
  }
}