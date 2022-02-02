package com.mutualmobile.praxis.uichat

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.uichat.chatscreen.ChatScreenContent
import com.mutualmobile.praxis.uichat.models.ChatPresentation

@OptIn(
  ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
  androidx.constraintlayout.compose.ExperimentalMotionApi::class
)
@Composable
fun ChatScreenUI(
  modifier: Modifier,
  slackChannel: ChatPresentation.PraxisChannel,
  onBackClick: () -> Unit,
  viewModel: ChatThreadVM = hiltViewModel()
) {
  val scaffoldState = rememberScaffoldState()
  LaunchedEffect(key1 = Unit) {
    viewModel.requestFetch(slackChannel)
  }
  PraxisTheme {
    Scaffold(
      backgroundColor = PraxisColorProvider.colors.uiBackground,
      contentColor = PraxisColorProvider.colors.textSecondary,
      modifier = modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
      topBar = {
        ChatAppBar(onBackClick, slackChannel)
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .padding(innerPadding)
      ) {
        ChatScreenContent(viewModel)
      }
    }
  }

}


@Composable
private fun ChatAppBar(onBackClick: () -> Unit, slackChannel: ChatPresentation.PraxisChannel) {
  PraxisSurfaceAppBar(backgroundColor = PraxisColorProvider.colors.appBarColor) {
    IconButton(onClick = { onBackClick() }) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        tint = PraxisColorProvider.colors.appBarIconColor,
        modifier = Modifier.size(24.dp)
      )
    }
    Column(
      Modifier.weight(1f),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = " ${if (slackChannel.isPrivate) lock() else "#"}  ${slackChannel.name}",
        style = PraxisTypography.subtitle1.copy(
          fontWeight = FontWeight.Bold,
          color = PraxisColorProvider.colors.appBarTextTitleColor
        ), modifier = Modifier.padding(2.dp)
      )
      Text(
        text = "25 members >",
        style = PraxisTypography.caption.copy(
          fontWeight = FontWeight.Normal,
          color = PraxisColorProvider.colors.appBarTextSubTitleColor
        ),
        modifier = Modifier.padding(2.dp)
      )
    }
    IconButton(onClick = { }) {
      Icon(
        imageVector = Icons.Default.Call,
        contentDescription = null,
        tint = PraxisColorProvider.colors.appBarIconColor,
        modifier = Modifier
          .size(24.dp)
      )
    }
  }
}

fun lock() = "\uD83D\uDD12"

enum class BoxState { Collapsed, Expanded }