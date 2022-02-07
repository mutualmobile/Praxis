package com.mutualmobile.uirandomusers.randomusers

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mutualmobile.uirandomusers.model.UiLayer.RandomUser
import java.text.DateFormat
import java.time.Instant
import java.util.Date

@RequiresApi(VERSION_CODES.O)
@Composable
fun RandomUsersList(usersList: List<RandomUser>) {
  LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(
          horizontal = 16.dp,
          vertical = 8.dp
      ),
      verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(usersList) { user ->
      RandomUserRow(user = user)
    }
  }
}

@RequiresApi(VERSION_CODES.O)
@Composable
fun RandomUserRow(user: RandomUser) {
  Column(modifier = Modifier.fillMaxWidth()) {
    Row(modifier = Modifier.fillMaxWidth()) {
      Image(
          painter = rememberImagePainter(user.picture?.medium),
          contentDescription = null,
          modifier = Modifier.size(128.dp)
      )
      Column(
          modifier = Modifier
              .fillMaxWidth()
              .padding(start = 16.dp)
      ) {
        Row(modifier = Modifier.fillMaxWidth()) {
          Text(text = user.name?.title.orEmpty())
          Text(text = user.name?.first.orEmpty())
          Text(text = user.name?.last.orEmpty())
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = user.dob?.age.toString())
          Text(text = user.dob?.date.orEmpty().formatDob())
        }
        Text(text = user.gender.orEmpty())
        Row(modifier = Modifier.fillMaxWidth()) {
          Text(text = user.location?.city.orEmpty())
          Text(user.location?.state.orEmpty())
        }
        Text(
            user.location?.country.orEmpty()
        )
        Text(user.location?.postcode.toString())
        Text(text = user.phone.orEmpty())
      }
    }

  }
}
@RequiresApi(VERSION_CODES.O)
fun String.formatDob(): String {
  return DateFormat.getDateInstance().format(Date.from(Instant.parse(this)))
}