package com.mutualmobile.praxis.uidashboard.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.reusable.PraxisListItem
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.uidashboard.R
import com.mutualmobile.praxis.commonui.reusable.PraxisImageBox
import com.mutualmobile.praxis.uichat.channels.*
import com.mutualmobile.praxis.uichat.channels.views.PraxisAllChannels
import com.mutualmobile.praxis.uichat.channels.views.PraxisStarredChannels
import com.mutualmobile.praxis.uichat.models.ChatPresentation

@Composable
fun HomeScreenUI(
  appBarIconClick: () -> Unit,
  onItemClick: (ChatPresentation.PraxisChannel) -> Unit = {}
) {
  PraxisSurface(
    color = PraxisColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column() {
      PraxisMMTopAppBar(appBarIconClick)
      Column(Modifier.verticalScroll(rememberScrollState())) {
        JumpToText()
        ThreadsTile()
        Divider(color = PraxisColorProvider.colors.lineColor, thickness = 0.5.dp)
        PraxisRecentChannels({
          onItemClick(it)
        })
        PraxisStarredChannels({
          onItemClick(it)
        })
        PraxisDirectMessages({
          onItemClick(it)
        })
        PraxisAllChannels({
          onItemClick(it)
        })
        PraxisConnections({
          onItemClick(it)
        })
      }
    }

  }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThreadsTile() {
  PraxisListItem(Icons.Default.MailOutline, stringResource(R.string.threads))
}

@Composable
fun JumpToText() {
  Box(
    Modifier
      .fillMaxWidth().clickable {  }
      .padding(8.dp)
  ) {
    RoundedCornerBoxDecoration {
      Text(
        text = "Jump to...",
        style = PraxisTypography.subtitle2.copy(color = PraxisColorProvider.colors.textPrimary),
        modifier = Modifier.fillMaxWidth()
      )
    }
  }

}

@Composable
private fun PraxisMMTopAppBar(appBarIconClick: () -> Unit) {
  PraxisSurfaceAppBar(
    title = {
      Text(text = "mutualmobile", style = PraxisTypography.h5.copy(color = Color.White))
    },
    navigationIcon = {
      MMImageButton(appBarIconClick)
    },
    backgroundColor = PraxisColorProvider.colors.appBarColor,
  )
}

@Composable
fun MMImageButton(appBarIconClick: () -> Unit) {
  IconButton(onClick = {
    appBarIconClick()
  }) {
    PraxisImageBox(
      Modifier.size(38.dp),
      "https://avatars.slack-edge.com/2018-07-20/401750958992_1b07bb3c946bc863bfc6_88.png"
    )
  }
}