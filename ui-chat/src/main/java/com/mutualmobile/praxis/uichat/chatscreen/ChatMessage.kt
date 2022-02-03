package com.mutualmobile.praxis.uichat.chatscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.reusable.PraxisImageBox
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatMessage(message: PraxisMessage) {
  ListItem(icon = {
    PraxisImageBox(Modifier.size(48.dp), imageUrl = "http://placekitten.com/200/300")
  }, modifier = Modifier.padding(2.dp), secondaryText = {
    ChatMedia(message)
  }, text = {
    ChatUserDateTime(message)
  })
}

@Composable
fun ChatMedia(message: PraxisMessage) {
  Column {
    Text(
      message.message,
      style = PraxisTypography.subtitle2.copy(
        color = PraxisColorProvider.colors.textSecondary
      ), modifier = Modifier.padding(4.dp)
    )
    PraxisImageBox(
      modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(228.dp), imageUrl = "http://placekitten.com/300/300"
    )
  }

}

@Composable
fun ChatUserDateTime(message: PraxisMessage) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text(
      message.createdBy + " \uD83C\uDF34",
      style = PraxisTypography.subtitle1.copy(
        fontWeight = FontWeight.Bold,
        color = PraxisColorProvider.colors.textPrimary
      ), modifier = Modifier.padding(4.dp)
    )
    Text(
      message.createdDate.calendar().formattedTime(),
      style = PraxisTypography.overline.copy(
        color = PraxisColorProvider.colors.textSecondary.copy(alpha = 0.8f)
      ), modifier = Modifier.padding(4.dp)
    )
  }
}

@SuppressLint("SimpleDateFormat")
fun Calendar.formattedMonthDate(): String {
  return SimpleDateFormat("MMM dd").format(this.time)
}

@SuppressLint("SimpleDateFormat")
fun Calendar.formattedTime(): String {
  return SimpleDateFormat("hh:mm a").format(this.time)
}

fun Long.calendar(): Calendar {
  return Calendar.getInstance().apply {
    this.timeInMillis = this@calendar
  }
}