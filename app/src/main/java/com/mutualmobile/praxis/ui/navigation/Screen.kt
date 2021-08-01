package com.mutualmobile.praxis.ui.navigation

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

sealed class Screen(val id: String) {
    object Home : Screen("Home")
    object Joke : Screen("Joke")
    object About : Screen("About")
}