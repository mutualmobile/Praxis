package com.mutualmobile.praxis.uionboarding.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@Composable
fun WorkspaceInputView(modifier: Modifier) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentWidth()
  ) {
    Text(
      text = "Workspace URL", style = PraxisTypography.caption.copy(
        color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.7f),
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Start
      ), modifier = Modifier.padding(bottom = 4.dp)
    )
    Row(
      modifier = modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {
      WorkspaceTF()
    }
  }
}


@Composable
private fun WorkspaceTF() {
  var workspace by remember { mutableStateOf("") }

  TextField(
    value = workspace,
    onValueChange = { newEmail ->
      workspace = newEmail
    },
    textStyle = textStyleField(),
    leadingIcon = {
      Text(text = "https://", style = textStyleField().copy(color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.4f)))
    },
    trailingIcon = {
      Text(".slack.com", style = textStyleField().copy(color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.4f)))
    },
    placeholder = {
      Text(
        text = "your-workspace",
        style = textStyleField(),
        textAlign = TextAlign.Start
      )
    },
    colors = textFieldColors(),
    singleLine = true,
    maxLines = 1,
  )
}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
  backgroundColor = Color.Transparent,
  cursorColor = PraxisColorProvider.colors.textPrimary,
  unfocusedIndicatorColor = Color.Transparent,
  focusedIndicatorColor = Color.Transparent
)

@Composable
private fun textStyleField() = PraxisTypography.h6.copy(
  color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.7f),
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)