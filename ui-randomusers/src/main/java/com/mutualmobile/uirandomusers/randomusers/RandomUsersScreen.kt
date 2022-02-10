package com.mutualmobile.uirandomusers.randomusers

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.uirandomusers.constants.Constants
import com.mutualmobile.uirandomusers.model.UiLayer.RandomUser
import com.mutualmobile.uirandomusers.model.UiLayer.RandomUserResponse
import com.mutualmobile.uirandomusers.randomusers.RandomUsersVM.UiState.Data

@Composable
fun RandomUsersScreen(randomUsersVM: RandomUsersVM = hiltViewModel()) {
  PraxisTheme() {
    Scaffold(
        backgroundColor = PraxisColorProvider.colors.uiBackground,
        contentColor = PraxisColorProvider.colors.textSecondary,
        modifier = Modifier.navigationBarsPadding().systemBarsPadding(),
        topBar = {
          PraxisSurfaceAppBar(
              title = {
                Text(
                    text = Constants.RANDOM_USERS_APP_BAR_TITLE,
                    style = PraxisTypography.h5.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
              },
              backgroundColor = PraxisColorProvider.colors.appBarColor,
          )
        }) {
      PraxisSurface(
          color = PraxisColorProvider.colors.uiBackground,
          modifier = Modifier
              .fillMaxSize()
      ) {

        val uiState by randomUsersVM.uiState.collectAsState()
        if (uiState is Data) {
          RandomUsersList(usersList = (uiState as Data).data.results.orEmpty())
        }
      }
    }
  }
}