package com.mutualmobile.praxis.navigator.directions

import androidx.navigation.NamedNavArgument

interface NavigationCommand {

  val arguments: List<NamedNavArgument>

  val destination: String
}