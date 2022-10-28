package com.mutualmobile.praxis.uionboarding.compose

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.AbsComposeNavigator
import com.mutualmobile.praxis.uionboarding.R

@Composable
fun EmailAddressInputUI(absComposeNavigator: AbsComposeNavigator) {
  PraxisTheme() {
    CommonInputUI(
      absComposeNavigator,
      { modifier ->
        EmailInputView(modifier)
      },
      stringResource(id = R.string.subtitle_this_email_slack)
    )
  }
}

@Composable
fun WorkspaceInputUI(absComposeNavigator: AbsComposeNavigator) {
  PraxisTheme() {
    CommonInputUI(
      absComposeNavigator,
      {
        WorkspaceInputView(it)
      },
      stringResource(id = R.string.subtitle_this_address_slack)
    )
  }
}

