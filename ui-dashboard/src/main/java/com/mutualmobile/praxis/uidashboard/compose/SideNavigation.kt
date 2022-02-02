package com.mutualmobile.praxis.uidashboard.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.*
import com.mutualmobile.praxis.commonui.reusable.PraxisImageBox
import com.mutualmobile.praxis.commonui.theme.*
import com.mutualmobile.praxis.commonui.reusable.PraxisListItem
import com.mutualmobile.praxis.uidashboard.R

@Composable
fun SideNavigation(modifier: Modifier) {
  PraxisSurface(color = PraxisColorProvider.colors.uiBackground , modifier = modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        WorkspacesBar()
        Spacer(modifier = Modifier.padding(8.dp))
        Workspace(selected = true)
        Spacer(modifier = Modifier.padding(8.dp))
        Workspace(selected = false)
      }
      Spacer(modifier = Modifier.padding(8.dp))
      SideNavFooter()
    }

  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SideNavFooter() {
  Column(modifier = Modifier.navigationBarsPadding()) {
    Divider(color = PraxisColorProvider.colors.lineColor)
    PraxisListItem(Icons.Filled.AddCircle, stringResource(id = R.string.add_workspace))
    PraxisListItem(Icons.Filled.Settings, stringResource(id = R.string.preferences))
    PraxisListItem(Icons.Filled.CheckCircle, stringResource(id = R.string.help))
  }
}

@Composable
fun Workspace(selected: Boolean) {
  Box(
    Modifier.background(
      color = if (selected) PraxisColorProvider.colors.textPrimary.copy(alpha = 0.2f) else Color.Transparent,
      shape = RoundedCornerShape(12.dp)
    )
  ) {
    Row(
      modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
      OrganizationLogo()
      Box(Modifier.weight(1f)) {
        OrganizationDetails()
      }
      Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null, tint = PraxisColorProvider.colors.textPrimary)
    }
  }
}

@Composable
fun OrganizationDetails() {
  Column(
    modifier = Modifier
      .padding(start = 8.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start
  ) {
    Text(
      text = stringResource(R.string.mutualmobile),
      style = PraxisTypography.h6.copy(color = PraxisColorProvider.colors.textPrimary, fontWeight = FontWeight.SemiBold)
    )
    Text(
      stringResource(R.string.mmlink),
      style = PraxisTypography.subtitle1.copy(
        fontWeight = FontWeight.Normal,
        color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.4f)
      ),
    )
  }
}

@Composable
fun OrganizationLogo() {
  Box(
    Modifier
      .size(68.dp)
      .border(
        width = 3.dp,
        color = PraxisColorProvider.colors.textPrimary,
        shape = RoundedCornerShape(12.dp)
      )
      .padding(8.dp)
  ) {
    PraxisImageBox(
      Modifier.size(64.dp),
      "https://avatars.slack-edge.com/2018-07-20/401750958992_1b07bb3c946bc863bfc6_88.png"
    )
  }
}

@Composable
private fun WorkspacesBar() {
  PraxisSurfaceAppBar(
    backgroundColor = PraxisColorProvider.colors.uiBackground,
    elevation = 0.dp,
    contentPadding = rememberInsetsPaddingValues(
      insets = LocalWindowInsets.current.statusBars,
      applyStart = true,
      applyTop = true,
      applyEnd = true,
    )
  ) {
    Text(
      text = stringResource(id = R.string.head_workspaces),
      style = PraxisTypography.h5.copy(
        color = PraxisColorProvider.colors.textPrimary,
        fontFamily = praxisFontFamily,
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier.padding(start = 8.dp)
    )
  }
}