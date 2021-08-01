package com.mutualmobile.praxis.ui.screen.joke

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

@Composable
fun JokeScreen(viewModel: ShowJokeVM) {
    val jokes by remember { viewModel.jokes }
    Scaffold {
        Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = jokes)
        }
    }
}