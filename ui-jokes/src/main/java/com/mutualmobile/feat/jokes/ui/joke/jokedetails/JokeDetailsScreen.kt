package com.mutualmobile.feat.jokes.ui.joke.jokedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@Composable
fun JokeDetailsScreen(jokeDetailVM: JokeDetailVM = hiltViewModel()) {

 PraxisTheme() {
   Scaffold(
     backgroundColor = PraxisColorProvider.colors.uiBackground,
     contentColor = PraxisColorProvider.colors.textSecondary,
     modifier = Modifier,
     topBar = {
       PraxisSurfaceAppBar(
         title = {
           Text(
             text = "Joke Detail",
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
         .fillMaxHeight()
         .fillMaxWidth(),
     ) {
       Column(
         modifier = Modifier
           .fillMaxHeight()
           .fillMaxWidth()
       ) {
         val uiState by jokeDetailVM.uiState.collectAsState()
         if (uiState is JokeDetailVM.UiState.SuccessState) {
           Text(
             text = (uiState as JokeDetailVM.UiState.SuccessState).joke.joke,
             Modifier.padding(16.dp)
           )
         }
       }
     }
   }
 }
}