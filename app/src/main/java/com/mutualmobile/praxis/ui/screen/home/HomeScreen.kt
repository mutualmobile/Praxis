package com.mutualmobile.praxis.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.ui.component.TopBar

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               viewModel: HomeVM,
               onShowJokeClick: (List<Joke>) -> Unit,
               onAboutClick: () -> Unit) {

    val state by remember { viewModel.viewState }

    if (state is HomeViewState.ShowJokes) {
        onShowJokeClick((state as HomeViewState.ShowJokes).jokes)
    }

    HomeScreen(modifier = modifier,
            state = state,
            onShowJokeClick = { viewModel.loadJokes() },
            onAboutClick = { onAboutClick() })
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview("Initial State")
fun HomeScreenInitialPreview() {
    HomeScreen(state = HomeViewState.Initial,
            onShowJokeClick = { },
            onAboutClick = { }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview("Loading State")
fun HomeScreenLoadingPreview() {
    HomeScreen(state = HomeViewState.Loading,
            onShowJokeClick = { },
            onAboutClick = { }
    )
}

@ExperimentalAnimationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               state: HomeViewState,
               onShowJokeClick: () -> Unit,
               onAboutClick: () -> Unit) {
    Scaffold(topBar = { TopBar() }) {

        Column(modifier = Modifier
                .fillMaxSize()
                .then(modifier),
                horizontalAlignment = Alignment.CenterHorizontally) {

            AnimatedVisibility(visible = state == HomeViewState.Loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))

            Text(text = stringResource(id = R.string.heading))

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_50dp)))

            Button(onClick = { onShowJokeClick() }) {
                Text(text = stringResource(id = R.string.show_random_jokes_coroutine))
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_10dp)))

            Button(onClick = { onAboutClick() }) {
                Text(text = stringResource(id = R.string.about))
            }
        }
    }
}