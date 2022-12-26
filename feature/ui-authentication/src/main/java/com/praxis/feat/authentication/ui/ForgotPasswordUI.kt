package com.praxis.feat.authentication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.theme.*
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.vm.ForgotPasswordVM

@Composable
fun ForgotPasswordUI(forgotPasswordVM: ForgotPasswordVM = hiltViewModel()){
  PraxisTheme() {
    Scaffold(
      backgroundColor = PraxisColorProvider.colors.uiBackground,
      contentColor = PraxisColorProvider.colors.textSecondary,
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
      topBar = {
        PraxisSurfaceAppBar(
          title = {
            Text(
              text = "Forgot password",
              style = PraxisTypography.h5.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
              )
            )
          },
          backgroundColor = PraxisColorProvider.colors.appBarColor,
        )
      }) {
      ForgotPasswordSurface(forgotPasswordVM)
    }
  }

}

@Composable
private fun ForgotPasswordSurface(forgotPasswordVM: ForgotPasswordVM) {
  PraxisSurface(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth()
  ) {
    Column(
      Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        contentDescription = "Logo", Modifier.size(128.dp)
      )

      EmailTF(forgotPasswordVM)

      ForgotPasswordButton(forgotPasswordVM)

    }
  }
}

@Composable
private fun ForgotPasswordButton(forgotPasswordVM: ForgotPasswordVM) {
  Button(
    onClick = {
      forgotPasswordVM.navigateBack()
    }, Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisColorProvider.colors.buttonColor)
  ) {
    Text(
      text = "Reset Password",
      style = MaterialTheme.typography.body1.copy(color = PraxisColorProvider.colors.buttonTextColor)
    )
  }
}

@Composable
private fun EmailTF(forgotPasswordVM: ForgotPasswordVM) {
  TextField(
    value = forgotPasswordVM.email.value, onValueChange = {
      forgotPasswordVM.email.value = it
    },
    Modifier
      .padding(16.dp)
      .fillMaxWidth(), label = {
      Text(
        text = "Email",
        style = MaterialTheme.typography.body2.copy(color = PraxisColorProvider.colors.textPrimary)
      )
    },
    shape = PraxisShapes.large,
    leadingIcon = {
      Image(
        painter = painterResource(id = R.drawable.ic_email),
        contentDescription = "Email"
      )
    },
    colors = textFieldColors(),
    maxLines = 1,
    singleLine = true
  )
}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
  focusedIndicatorColor = Color.Transparent,
  disabledIndicatorColor = Color.Transparent,
  unfocusedIndicatorColor = Color.Transparent,
  backgroundColor = PraxisColorProvider.colors.accent.copy(alpha = AlphaNearTransparent),
)