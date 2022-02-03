package com.mutualmobile.praxis.uidashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.reusable.PraxisListItem
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.uidashboard.R
import com.mutualmobile.praxis.uidashboard.home.search.SearchCancel

@Composable
fun SearchMessagesUI() {
  PraxisSurface(
    color = PraxisColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column() {
      SearchTopAppBar()
      Content()
    }
  }
}

@Composable
private fun SearchTopAppBar() {
  PraxisSurfaceAppBar(
    backgroundColor = PraxisColorProvider.colors.appBarColor,
    contentPadding = PaddingValues(8.dp)
  ) {
    SearchCancel()
  }

}

@Composable
private fun Content() {
  Column(Modifier.verticalScroll(rememberScrollState())) {
    PraxisListItem(
      icon = Icons.Default.ShoppingCart,
      title = stringResource(R.string.browse_people)
    )
    PraxisListItem(icon = Icons.Default.Search, title = stringResource(R.string.browse_channels))
    PraxisListDivider()
    // Recent Searches
    SearchText(stringResource(R.string.recent_searches))
    repeat(5) {
      PraxisListItem(
        icon = Icons.Default.Favorite,
        title = "in:#android_india",
        trailingItem = Icons.Default.Clear
      )
    }
    PraxisListDivider()
    // Narrow Your Search
    SearchText(stringResource(R.string.narrow_your_search))
    repeat(5) {
      PraxisListItemTrailingView(
        icon = Icons.Default.Favorite,
        title = "from:",
        trailingView = {
          Text(text = "Ex: @zoemaxwell")
        }
      )
    }
  }
}

@Composable
private fun SearchText(title: String) {
  Text(
    text = title,
    style = PraxisTypography.caption.copy(fontWeight = FontWeight.SemiBold),
    modifier = Modifier.padding(16.dp)
  )
}

@Composable
fun PraxisListDivider() {
  Divider(color = PraxisColorProvider.colors.lineColor, thickness = 0.5.dp)
}

