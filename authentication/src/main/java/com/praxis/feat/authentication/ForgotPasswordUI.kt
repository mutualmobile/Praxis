package com.praxis.feat.authentication

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ForgotPasswordUI(forgotPasswordVM: ForgotPasswordVM = hiltViewModel()){
  Surface(color = MaterialTheme.colors.background){}

}