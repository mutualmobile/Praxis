package com.mutualmobile.praxis.uidashboard.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.reusable.PraxisListItem
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.uidashboard.R
import com.mutualmobile.praxis.commonui.reusable.PraxisImageBox

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserProfileUI() {
  PraxisSurface(
    color = PraxisColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
      SearchTopAppBar()
      UserHeader()
      Box(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        StatusBox()
      }
      PraxisListItem(Icons.Default.Notifications, stringResource(R.string.pause_notifications))
      PraxisListItem(Icons.Default.Person, stringResource(R.string.set_away))
      Divider(color = PraxisColorProvider.colors.lineColor, thickness = 0.5.dp)
      PraxisListItem(Icons.Default.FavoriteBorder, stringResource(R.string.saved_items))
      PraxisListItem(Icons.Default.Person, stringResource(R.string.view_profile))
      PraxisListItem(Icons.Default.Notifications, stringResource(R.string.notifications))
      PraxisListItem(Icons.Default.Settings, stringResource(R.string.preferences))
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  PraxisSurfaceAppBar(
    title = {
      Text(
        text = "You",
        style = PraxisTypography.h5.copy(
          color = Color.White,
          fontWeight = FontWeight.Bold
        )
      )
    },
    backgroundColor = PraxisColorProvider.colors.appBarColor,
  )
}


@Composable
fun PraxisListItemTrailingView(
  icon: ImageVector,
  title: String,
  trailingView: @Composable () -> Unit = {}
) {
  Row(modifier = Modifier
    .padding(12.dp)
    .clickable { }) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.4f),
      modifier = Modifier
        .size(24.dp)
        .padding(4.dp)
    )
    Text(
      text = title,
      style = PraxisTypography.subtitle1.copy(
        color = PraxisColorProvider.colors.textPrimary.copy(
          alpha = 0.8f
        )
      ), modifier = Modifier
        .weight(1f)
        .padding(4.dp)
    )
    trailingView()
  }
}



@Composable
fun UserHeader() {
  Row(Modifier.padding(12.dp)) {
    PraxisImageBox(
      Modifier.size(72.dp),
      "https://ca.slack-edge.com/T02TLUWLZ-U2ZG961MW-176c142f9265-512"
    )
    Column(Modifier.padding(start = 8.dp)) {
      Text(text = "Anmol Verma", style = PraxisTypography.h6.copy(fontWeight = FontWeight.Bold))
      Spacer(modifier = Modifier.padding(top = 4.dp))
      Text(
        text = "Active",
        style = PraxisTypography.subtitle1.copy(
          fontWeight = FontWeight.Bold,
          color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.4f)
        )
      )
    }
  }
}

@Composable
fun StatusBox() {
  RoundedCornerBoxDecoration {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text("🌴", modifier = Modifier.padding(4.dp), style = PraxisTypography.subtitle1)
      Text(
        text = "Out on a vacation", style = PraxisTypography.body1.copy(
          fontWeight = FontWeight.Normal,
          color = PraxisColorProvider.colors.textPrimary
        ), modifier = Modifier
          .padding(4.dp)
          .weight(1f),
        textAlign = TextAlign.Start
      )
      Icon(
        imageVector = Icons.Default.Clear,
        contentDescription = null,
        tint = PraxisColorProvider.colors.textPrimary
      )
    }
  }
}


@Composable
fun RoundedCornerBoxDecoration(content: @Composable () -> Unit) {
  Box(
    Modifier
      .border(
        width = 1.dp,
        color = PraxisColorProvider.colors.lineColor,
        shape = RoundedCornerShape(12.dp)
      )
      .padding(16.dp)
  ) {
    content()
  }
}