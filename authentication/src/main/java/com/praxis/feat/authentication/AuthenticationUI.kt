package com.praxis.feat.authentication

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthenticationUI(authVM: AuthVM = hiltViewModel()) {
  Scaffold(
    backgroundColor = MaterialTheme.colors.background,
    topBar = {
      TopAppBar(title = {
        Text(
          text = "Authentication",
          color = MaterialTheme.colors.onPrimary,
          textAlign = TextAlign.Start,
        )
      })
    }) {
    Surface(color = MaterialTheme.colors.background) {
      Column(Modifier.padding(16.dp)) {

        TextField(value = authVM.email.value, onValueChange = {
          authVM.email.value = it
        }, Modifier.padding(16.dp), label = { Text("Email") })

        TextField(value = authVM.password.value, onValueChange = {
          authVM.password.value = it
        }, Modifier.padding(16.dp), label = { Text("Password") })

        Button(onClick = {
          authVM.navigateDashboard()
        }, Modifier.fillMaxWidth()) {
          Text(text = "Login")
        }
      }
    }
  }

}