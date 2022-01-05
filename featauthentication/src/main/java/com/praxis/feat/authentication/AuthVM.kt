package com.praxis.feat.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(private val navigator: Navigator) : ViewModel() {
  fun navigateDashboard() {
    navigator.navigate(Screen.Jokes.route)
  }

  fun navigateForgotPassword() {
    navigator.navigate(Screen.ForgotPassword.route)
  }

  var password = mutableStateOf("")
  var email = mutableStateOf("")
}