package com.praxis.feat.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(private val navigator: Navigator):ViewModel() {
  var email = mutableStateOf("")

  fun navigateBack() {
    navigator.navigateUp()
  }
}