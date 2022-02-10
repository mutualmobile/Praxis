package com.mutualmobile.uirandomusers.randomusers

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.uirandomusers.model.UiLayer.RandomUser
import java.text.DateFormat
import java.time.Instant
import java.util.Date

@Composable
fun RandomUsersList(usersList: List<RandomUser>) {
  LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(
          horizontal = 8.dp,
          vertical = 8.dp
      ),
      verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    items(usersList) { user ->
      RandomUserRow(user = user)
    }
  }
}

@Composable
fun RandomUserRow(user: RandomUser) {
  val spaceModifier = Modifier.padding(end = 4.dp)
  Card(
      modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp),
      backgroundColor = PraxisColorProvider.colors.uiBackground,
      elevation = 8.dp
  ) {
    Column(modifier = Modifier.fillMaxSize().padding(all = 8.dp)) {
      Row(modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(user.picture?.medium),
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(bottom = 4.dp),
              horizontalArrangement = Arrangement.Start
          ) {
            Text(text = user.name?.title.orEmpty(), modifier = spaceModifier)
            Text(text = user.name?.first.orEmpty(), modifier = spaceModifier)
            Text(text = user.name?.last.orEmpty())
          }
          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(bottom = 4.dp),
              horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = user.dob?.age.toString(), modifier = spaceModifier)
            Text(text = user.dob?.date.orEmpty().formatDob())
          }
          Text(text = user.gender.orEmpty(), modifier = Modifier.padding(bottom = 4.dp))
          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(bottom = 4.dp),
              horizontalArrangement = Arrangement.Start
          ) {
            Text(text = user.location?.city.orEmpty(), modifier = spaceModifier)
            Text(user.location?.state.orEmpty())
          }
          Text(
              user.location?.country.orEmpty(), modifier = Modifier.padding(bottom = 4.dp)
          )
          Text(user.location?.postcode.toString(), modifier = Modifier.padding(bottom = 4.dp))
          Text(text = user.phone.orEmpty(), modifier = Modifier.padding(bottom = 4.dp))
        }
      }
    }

  }
}

fun String.formatDob(): String {
  return DateFormat.getDateInstance().format(Date.from(Instant.parse(this)))
}