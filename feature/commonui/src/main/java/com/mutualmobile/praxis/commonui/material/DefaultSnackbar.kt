package com.mutualmobile.praxis.commonui.material

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@Composable
fun DefaultSnackbar(
  snackbarHostState: SnackbarHostState,
  modifier: Modifier = Modifier,
  onDismiss: () -> Unit = { }
) {
  SnackbarHost(
    hostState = snackbarHostState,
    snackbar = { data ->
      Snackbar(
        content = {
          Text(
            text = data.message,
            style = PraxisTypography.body1,
            color = PraxisColorProvider.colors.textPrimary,
            )
        },
        action = {
          data.actionLabel?.let { actionLabel ->
            TextButton(onClick = onDismiss) {
              Text(
                text = actionLabel,
                color = PraxisColorProvider.colors.textPrimary,
                style = PraxisTypography.body2
              )
            }
          }
        },
        backgroundColor = PraxisColorProvider.colors.uiBackground
      )
    },
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight(Alignment.Bottom)
  )
}