package com.mutualmobile.praxis.uionboarding.compose

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.uionboarding.R

@Composable
fun EmailAddressInputUI(composeNavigator: ComposeNavigator) {
  PraxisTheme() {
    CommonInputUI(
      composeNavigator,
      { modifier ->
        EmailInputView(modifier)
      },
      stringResource(id = R.string.subtitle_this_email_slack)
    )
  }
}

@Composable
fun WorkspaceInputUI(composeNavigator: ComposeNavigator) {
  PraxisTheme() {
    CommonInputUI(
      composeNavigator,
      {
        WorkspaceInputView(it)
      },
      stringResource(id = R.string.subtitle_this_address_slack)
    )
  }
}

