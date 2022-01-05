package com.praxis.feat.authentication

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.material.DefaultSnackbar
import com.mutualmobile.praxis.commonui.theme.*

@Composable
fun AuthenticationUI(authVM: AuthVM = hiltViewModel()) {
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
      AuthSurface(authVM, scaffoldState)
      DefaultSnackbar(scaffoldState.snackbarHostState, Modifier.padding(8.dp)) {
        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
      }
    }

  }

}

@Composable
private fun AuthSurface(authVM: AuthVM, scaffoldState: ScaffoldState) {
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

      val resetPasswordState by authVM.passwordResetFlow.collectAsState()
      Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        contentDescription = "Logo", Modifier.size(128.dp)
      )

      EmailTF(authVM)

      PasswordTF(authVM)

      LoginButton(authVM)

      ForgotPasswordText(authVM)

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
fun ForgotPasswordText(authVM: AuthVM) {
  ClickableText(text = buildAnnotatedString {

    withStyle(
      style = SpanStyle(
        color = PraxisTheme.colors.accent,
      )
    ) {
      append("Forgot Password? ")
    }

  }, onClick = {
    authVM.navigateForgotPassword()
  }, modifier = Modifier.padding(8.dp))
}

@Composable
private fun LoginButton(authVM: AuthVM) {
  Button(
    onClick = {
      authVM.navigateDashboard()
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
private fun PasswordTF(authVM: AuthVM) {
  val password by authVM.password.collectAsState()
  TextField(
    value = password,
    onValueChange = {
      authVM.password.value = it
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
private fun EmailTF(authVM: AuthVM) {
  val email by authVM.email.collectAsState()
  TextField(
    value = email, onValueChange = {
      authVM.email.value = it
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