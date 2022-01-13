package com.praxis.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.material.CustomSnackbar
import com.mutualmobile.praxis.commonui.theme.*
import com.praxis.feat.authentication.R

@Composable
fun AuthenticationUI(loginVM: LoginVM = hiltViewModel()) {
  val scaffoldState = rememberScaffoldState()
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar(titleText = "Authentication")
    }, scaffoldState = scaffoldState, snackbarHost = {
      scaffoldState.snackbarHostState
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      AuthSurface(loginVM, scaffoldState)
      CustomSnackbar(scaffoldState.snackbarHostState) {
        loginVM.snackBarState.value = ""
        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
      }
    }

  }

}

@Composable
private fun AuthSurface(loginVM: LoginVM, scaffoldState: ScaffoldState) {
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

      val resetPasswordState by loginVM.snackBarState.collectAsState()
      Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        contentDescription = "Logo", Modifier.size(128.dp)
      )

      EmailTF(loginVM)

      PasswordTF(loginVM)

      LoginButton(loginVM)

      ForgotPasswordText(loginVM)

      if (resetPasswordState.isNotEmpty()) {
        LaunchedEffect(scaffoldState) {
          scaffoldState.snackbarHostState.showSnackbar(
            message = resetPasswordState,
            actionLabel = "Ok"
          )
        }
      }
    }
  }
}

@Composable
fun ForgotPasswordText(loginVM: LoginVM) {
  ClickableText(text = buildAnnotatedString {

    withStyle(
      style = SpanStyle(
        color = PraxisTheme.colors.accent,
      )
    ) {
      append("Forgot Password? ")
    }

  }, onClick = {
    loginVM.navigateForgotPassword()
  }, modifier = Modifier.padding(8.dp))
}

@Composable
private fun LoginButton(loginVM: LoginVM) {
  Button(
    onClick = {
      loginVM.login()
    }, Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisTheme.colors.buttonColor)
  ) {
    Text(
      text = "Login",
      style = MaterialTheme.typography.body1.copy(color = PraxisTheme.colors.buttonTextColor)
    )
  }
}

@Composable
private fun PasswordTF(loginVM: LoginVM) {
  val credentials by loginVM.credentials.collectAsState()
  TextField(
    value = credentials.password ?: "",
    onValueChange = {
      loginVM.credentials.value = credentials.copy(password = it)
    },
    Modifier
      .padding(16.dp)
      .fillMaxWidth(),
    label = {
      Text(
        text = "Password",
        style = MaterialTheme.typography.body2.copy(color = PraxisTheme.colors.textPrimary)
      )
    },
    shape = PraxisShapes.large,
    leadingIcon = {
      Image(
        painter = painterResource(id = R.drawable.ic_eye),
        contentDescription = "email"
      )
    },
    colors = textFieldColors(),
    maxLines = 1,
    singleLine = true,
    visualTransformation = PasswordVisualTransformation()
  )
}

@Composable
private fun EmailTF(loginVM: LoginVM) {
  val credentials by loginVM.credentials.collectAsState()
  TextField(
    value = credentials.email ?: "",
    onValueChange = {
      loginVM.credentials.value = credentials.copy(email = it)
    },
    Modifier
      .padding(16.dp)
      .fillMaxWidth(), label = {
      Text(
        text = "Email",
        style = MaterialTheme.typography.body2.copy(color = PraxisTheme.colors.textPrimary)
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
  backgroundColor = PraxisTheme.colors.accent.copy(alpha = AlphaNearTransparent),
)


@Preview("Light+Dark")
@Composable
fun PreviewAuth() {
  PraxisTheme(darkTheme = true) {
    AuthenticationUI()
  }
}