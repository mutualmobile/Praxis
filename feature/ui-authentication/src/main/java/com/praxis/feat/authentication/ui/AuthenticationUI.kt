package com.praxis.feat.authentication.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.DefaultSnackbar
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.theme.*
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.vm.AuthVM
import com.praxis.feat.authentication.vm.streamProgress
import com.praxis.feat.authentication.vm.uri

@Composable
fun AuthenticationUI(
  authVM: AuthVM = hiltViewModel(),
) {
  PraxisTheme {
    val scaffoldState = rememberScaffoldState()
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
              text = "Authentication",
              style = PraxisTypography.h5.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
              )
            )
          },
          backgroundColor = PraxisColorProvider.colors.appBarColor,
        )
      }, scaffoldState = scaffoldState, snackbarHost = {
        scaffoldState.snackbarHostState
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {
        AuthSurface(
          authVM = authVM, scaffoldState = scaffoldState
        )
        DefaultSnackbar(scaffoldState.snackbarHostState) {
          authVM.snackBarState.value = ""
          scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
        }
      }

    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun AuthSurface(
  authVM: AuthVM,
  scaffoldState: ScaffoldState,
) {
  PraxisSurface(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth()
  ) {

    val resetPasswordState by authVM.snackBarState.collectAsState()
    val uiState by authVM.formUiState.collectAsState()
    val randomPhotoState by authVM.randomPhotoState.collectAsState()

    Box() {
      AnimatedVisibility(visible = uiState is AuthVM.UiState.SuccessState || randomPhotoState is AuthVM.UiState.Streaming) {
        Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Box(Modifier.background(Color.Black)) {
            Image(
              painter = rememberImagePainter(randomPhotoState.uri()),
              contentScale = ContentScale.Crop,
              contentDescription = null, modifier = Modifier.fillMaxSize(),
            )

            Column(
              Modifier.align(Alignment.BottomCenter),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = randomPhotoState.streamProgress() ?: "",
                style = PraxisTypography.subtitle1.copy(color = PraxisColorProvider.colors.appBarTextTitleColor)
              )

              RandomPhotoButton(authVM)

              LogoutButton(authVM)
            }
          }


        }
      }
      Column(
        Modifier
          .padding(16.dp)
          .navigationBarsWithImePadding()
          .fillMaxWidth()
          .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

        val (focusRequester) = FocusRequester.createRefs()

        AnimatedVisibility(visible = randomPhotoState !is AuthVM.UiState.Streaming) {
          Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Logo", Modifier.size(128.dp)
          )
        }

        AnimatedVisibility(visible = uiState is AuthVM.UiState.Empty) {
          EmailTF(authVM, focusRequester)
        }

        AnimatedVisibility(visible = uiState is AuthVM.UiState.Empty) {
          PasswordTF(authVM, focusRequester)
        }

        AnimatedVisibility(visible = (uiState is AuthVM.UiState.LoadingState)) {
          CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        }

        AnimatedVisibility(visible = uiState is AuthVM.UiState.Empty) {
          LoginButton(authVM = authVM)
        }

        AnimatedVisibility(visible = uiState is AuthVM.UiState.Empty) {
          ForgotPasswordText(authVM)
        }

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
}


@Composable
fun RandomPhotoButton(authVM: AuthVM) {
  Button(
    onClick = {
      authVM.fetchRandomPhoto()
    }, Modifier.wrapContentWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisColorProvider.colors.buttonColor)
  ) {
    Text(
      text = "Load Random Photo",
      style = MaterialTheme.typography.body1.copy(color = PraxisColorProvider.colors.buttonTextColor)
    )
  }
}

@Composable
fun LogoutButton(authVM: AuthVM) {
  Button(
    onClick = {
      authVM.logout()
    }, Modifier.wrapContentWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisColorProvider.colors.buttonColor)
  ) {
    Text(
      text = "Logout",
      style = MaterialTheme.typography.body1.copy(color = PraxisColorProvider.colors.buttonTextColor)
    )
  }
}

@Composable
fun ForgotPasswordText(authVM: AuthVM) {
  ClickableText(text = buildAnnotatedString {

    withStyle(
      style = SpanStyle(
        color = PraxisColorProvider.colors.textPrimary,
      )
    ) {
      append("Forgot Password? ")
    }

  }, onClick = {
    authVM.navigateForgotPassword()
  }, modifier = Modifier.padding(8.dp))
}

@Composable
private fun LoginButton(
  authVM: AuthVM,
) {
  Button(
    onClick = {
      authVM.loginNow()
    }, Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(backgroundColor = PraxisColorProvider.colors.buttonColor)
  ) {
    Text(
      text = "Login",
      style = MaterialTheme.typography.body1.copy(color = PraxisColorProvider.colors.buttonTextColor)
    )
  }
}

@ExperimentalComposeUiApi
@Composable
private fun PasswordTF(authVM: AuthVM, focusRequester: FocusRequester) {
  val credentials by authVM.credentials.collectAsState()
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = credentials.password ?: "",
    onValueChange = {
      authVM.credentials.value = credentials.copy(password = it)
    },
    modifier = Modifier
      .padding(16.dp)
      .focusRequester(focusRequester)
      .fillMaxWidth(),
    label = {
      Text(
        text = "Password",
        style = MaterialTheme.typography.body2.copy(color = PraxisColorProvider.colors.textPrimary)
      )
    },
    shape = PraxisShapes.large,
    keyboardActions = KeyboardActions(
      onDone = { keyboardController?.hide() }),
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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

@ExperimentalComposeUiApi
@Composable
private fun EmailTF(authVM: AuthVM, focusRequester: FocusRequester) {
  val credentials by authVM.credentials.collectAsState()

  TextField(
    value = credentials.email ?: "",
    onValueChange = {
      authVM.credentials.value = credentials.copy(email = it)
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
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
    ),
    keyboardActions = KeyboardActions(
      onNext = {
        focusRequester.requestFocus()
      },
    ),
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

@Preview("Light+Dark")
@Composable
fun PreviewAuth() {
  PraxisTheme(isDarkTheme = true) {
    AuthenticationUI()
  }
}