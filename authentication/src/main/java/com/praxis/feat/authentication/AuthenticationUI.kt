package com.praxis.feat.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.*

@Composable
fun AuthenticationUI(authVM: AuthVM = hiltViewModel()) {
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar(titleText = "Authentication")
    }) {
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

        TextField(
          value = authVM.email.value, onValueChange = {
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
          colors = textFieldColors()
        )

        TextField(
          value = authVM.password.value,
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
          colors = textFieldColors()
        )

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
    }
  }

}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
  focusedIndicatorColor = Color.Transparent,
  disabledIndicatorColor = Color.Transparent,
  unfocusedIndicatorColor = Color.Transparent,
  backgroundColor = PraxisTheme.colors.accent.copy(alpha = AlphaNearTransparent),
)