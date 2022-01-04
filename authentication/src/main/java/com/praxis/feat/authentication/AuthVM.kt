package com.praxis.feat.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.directions.DashboardDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(private val navigator: Navigator) : ViewModel() {
  fun navigateDashboard() {
    navigator.navigate(DashboardDirections.root)
  }

  var password = mutableStateOf("")
  var email= mutableStateOf("")
}