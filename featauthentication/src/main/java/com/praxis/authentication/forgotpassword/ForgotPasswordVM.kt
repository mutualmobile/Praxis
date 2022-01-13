package com.praxis.authentication.forgotpassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(private val navigator: Navigator) : ViewModel() {
  var email = mutableStateOf("")

  fun navigateBack() {
    navigator.navigateBackWithResult(
      NavigationKeys.ForgotPassword, "Reset Password Done!",
      Screen.Auth.route
    )
  }
}