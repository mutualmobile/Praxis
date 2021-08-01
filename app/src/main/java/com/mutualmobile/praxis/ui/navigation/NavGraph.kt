package com.mutualmobile.praxis.ui.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mutualmobile.praxis.domain.model.Joke
import com.mutualmobile.praxis.ui.screen.home.HomeScreen
import com.mutualmobile.praxis.ui.screen.home.HomeVM
import com.mutualmobile.praxis.ui.screen.home.about.AboutScreen
import com.mutualmobile.praxis.ui.screen.home.about.AboutVM
import com.mutualmobile.praxis.ui.screen.joke.JokeScreen
import com.mutualmobile.praxis.ui.screen.joke.ShowJokeVM
import kotlin.collections.ArrayList

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

@Composable
fun NavGraph(modifier: Modifier = Modifier,
             navController: NavHostController = rememberNavController(),
             startDestination: Screen = Screen.Home) {

    NavHost(navController = navController,
            startDestination = startDestination.id) {

        composable(Screen.Home.id) {
            HomeScreen(
                    modifier = modifier,
                    viewModel = hiltViewModel<HomeVM>(),
                    onShowJokeClick = { jokeList ->
                        with(navController) {
                            currentBackStackEntry?.arguments = Bundle().apply {
                                putParcelableArrayList("list", jokeList as ArrayList<out Parcelable>)
                            }
                            navigate(Screen.Joke.id)
                        }
                    },
                    onAboutClick = {
                        navController.navigate(Screen.About.id)
                    })
        }

        composable(Screen.About.id) {
            AboutScreen(hiltViewModel<AboutVM>())
        }

        composable(Screen.Joke.id) {
            val list = navController.previousBackStackEntry?.arguments?.getParcelableArrayList<Joke>("list") as List<Joke>
            JokeScreen(hiltViewModel<ShowJokeVM>(), list)
        }
    }
}