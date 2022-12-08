package com.mutualmobile.praxis.uionboarding.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.theme.*
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisScreen
import com.mutualmobile.praxis.uionboarding.R

@Composable
fun SkipTypingUI(composeNavigator: ComposeNavigator) {
  PraxisTheme() {
    val scaffoldState = rememberScaffoldState()
    val sysUiController = rememberSystemUiController()
    SideEffect {
      sysUiController.setNavigationBarColor(color = PraxisCloneColor)
      sysUiController.setSystemBarsColor(color = PraxisCloneColor)
    }
    Scaffold(
      backgroundColor = PraxisCloneColor,
      contentColor = PraxisColorProvider.colors.textSecondary,
      modifier = Modifier.statusBarsPadding(), scaffoldState = scaffoldState,
      topBar = {
        PraxisSurfaceAppBar(
          title = {

          },
          navigationIcon = {
            IconButton(onClick = {
              composeNavigator.navigateUp()
            }) {
              Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Clear",
                modifier = Modifier.padding(start = 8.dp), tint = Color.White
              )
            }
          },
          backgroundColor = PraxisCloneColor,
          elevation = 0.dp
        )
      },
      snackbarHost = {
        scaffoldState.snackbarHostState
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {
        PraxisSurface(
          color = PraxisCloneColor,
          modifier = Modifier
            .padding(28.dp)
        ) {
          Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .fillMaxHeight()
              .fillMaxWidth()
          ) {
            Image(
              painter = painterResource(id = R.drawable.gettingstarted),
              contentDescription = "Logo",
              Modifier
            )
            TitleSubtitleText()
            Spacer(Modifier.padding(8.dp))
            Column {
              EmailMeMagicLink(composeNavigator)
              Box(modifier = Modifier.height(12.dp))
              IWillSignInManually(composeNavigator)
            }

          }

        }
      }

    }
  }


}

@Composable
fun EmailMeMagicLink(composeNavigator: ComposeNavigator) {
  OutlinedButton(
    onClick = {
      composeNavigator.navigate(PraxisScreen.EmailAddressInputUI.name)
    },
    border = BorderStroke(1.dp, color = Color.White),
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
    modifier = Modifier
      .fillMaxWidth()
      .height(40.dp),
  ) {
    Text(
      text = "Email me a magic link",
      style = PraxisTypography.subtitle2.copy(color = Color.White)
    )
  }
}

@Composable
private fun IWillSignInManually(composeNavigator: ComposeNavigator) {
  Button(
    onClick = {
      composeNavigator.navigate(PraxisScreen.WorkspaceInputUI.name)
    },
    Modifier
      .fillMaxWidth()
      .height(40.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
  ) {
    Text(
      text = "I'll sign in manually",
      style = PraxisTypography.subtitle2.copy(color = PraxisCloneColor)
    )
  }
}

@Composable
private fun TitleSubtitleText(modifier: Modifier = Modifier) {
  Text(
    text = buildAnnotatedString {
      withStyle(
        style = SpanStyle(
          fontFamily = praxisFontFamily,
          fontWeight = FontWeight.Bold,
          fontSize = PraxisTypography.h6.fontSize, color = Color.White
        )
      ) {
        append("Want to skip the typing ?\n\n")
      }
      withStyle(
        style = SpanStyle(
          fontFamily = praxisFontFamily,
          fontWeight = FontWeight.Normal,
          fontSize = PraxisTypography.subtitle2.fontSize, color = Color.White
        )
      ) {
        append("We can email you a magic sign-in link that adds all your workspaces at once")
      }
    },
    textAlign = TextAlign.Center,
    modifier = modifier.fillMaxWidth(),
    style = PraxisTypography.h4
  )
}
