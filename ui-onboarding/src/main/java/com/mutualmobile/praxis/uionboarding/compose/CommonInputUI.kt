package com.mutualmobile.praxis.uionboarding.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.PraxisRoute
import com.mutualmobile.praxis.navigator.PraxisScreen

@Composable
fun CommonInputUI(
  composeNavigator: ComposeNavigator,
  TopView: @Composable (modifier: Modifier) -> Unit,
  subtitleText: String
) {
  val scaffoldState = rememberScaffoldState()

  PraxisTheme {
    Scaffold(
      backgroundColor = PraxisColorProvider.colors.uiBackground,
      contentColor = PraxisColorProvider.colors.textSecondary,
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {
        PraxisSurface(
          color = PraxisColorProvider.colors.uiBackground,
          modifier = Modifier
        ) {
          ConstraintLayout(
            modifier = Modifier
              .padding(12.dp)
              .navigationBarsWithImePadding()
              .fillMaxHeight()
              .fillMaxWidth()
          ) {
            // Create references for the composables to constrain
            val (inputView, subtitle, button) = createRefs()

            TopView(modifier = Modifier.constrainAs(inputView) {
              top.linkTo(parent.top)
              bottom.linkTo(button.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            })
            SubTitle(modifier = Modifier.constrainAs(subtitle) {
              top.linkTo(inputView.bottom)
            }, subtitleText)
            NextButton(modifier = Modifier.constrainAs(button) {
              bottom.linkTo(parent.bottom)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }, composeNavigator)
          }
        }
      }

    }
  }
}

@Composable
fun NextButton(modifier: Modifier = Modifier, composeNavigator: ComposeNavigator) {
  Button(
    onClick = {
      composeNavigator.navigate(PraxisRoute.Auth.name) {
        this.popUpTo(PraxisRoute.OnBoarding.name) {
          this.inclusive = true
        }
      }
    },
    modifier
      .fillMaxWidth()
      .height(50.dp)
      .padding(top = 8.dp),
    colors = ButtonDefaults.buttonColors(
      backgroundColor = PraxisColorProvider.colors.buttonColor
    )
  ) {
    Text(
      text = "Next",
      style = PraxisTypography.subtitle2.copy(color = PraxisColorProvider.colors.buttonTextColor)
    )
  }
}

@Composable
private fun SubTitle(modifier: Modifier = Modifier, subtitleText: String) {
  Text(
    subtitleText,
    modifier = modifier
      .fillMaxWidth()
      .wrapContentWidth(align = Alignment.Start),
    style = PraxisTypography.subtitle2.copy(
      color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.8f),
      fontWeight = FontWeight.Normal,
      textAlign = TextAlign.Start
    )
  )
}

