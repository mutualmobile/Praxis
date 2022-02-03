package com.mutualmobile.praxis.uionboarding.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailInputView(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentWidth()
  ) {
    Text(
      text = "Email", style = PraxisTypography.caption.copy(
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
      EmailTF()
    }
  }

}

@ExperimentalComposeUiApi
@Composable
private fun EmailTF() {
  var email by remember { mutableStateOf("") }
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = email,
    onValueChange = { newEmail ->
      email = newEmail
    },
    textStyle = textStyleField(),
    leadingIcon = {
      Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = PraxisColorProvider.colors.textPrimary)
    },
    placeholder = {
      Text(
        text = "Your email address",
        style = textStyleField(),
        textAlign = TextAlign.Start
      )
    },
    keyboardOptions = KeyboardOptions.Default.copy(
      autoCorrect = false,
      keyboardType = KeyboardType.Email,
      imeAction = ImeAction.Done,
    ),
    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
    colors = textFieldColors(),
    singleLine = true,
    maxLines = 1
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
  color = PraxisColorProvider.colors.textPrimary,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start
)