package com.praxis.feat.authentication

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme

@Composable
fun ForgotPasswordUI(forgotPasswordVM: ForgotPasswordVM = hiltViewModel()){
  PraxisSurface( color = PraxisTheme.colors.uiBackground,
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth()){}

}