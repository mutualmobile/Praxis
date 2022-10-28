package com.praxis.feat.authentication.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.navigator.AbsComposeNavigator
import com.mutualmobile.praxis.navigator.NavigationKeys
import com.mutualmobile.praxis.navigator.PraxisScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(private val navigatorAbs: AbsComposeNavigator) : ViewModel() {
  var email = mutableStateOf("")

  fun navigateBack() {
    navigatorAbs.navigateBackWithResult(
      NavigationKeys.ForgotPassword, "Reset Password Done!",
      PraxisScreen.Auth.route
    )
  }
}