package com.mutualmobile.praxis.navigator.directions

import androidx.navigation.NamedNavArgument

object DashboardDirections {

  val root = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "home"

  }

  val jokeDetails = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "jokeDetails"

  }
}