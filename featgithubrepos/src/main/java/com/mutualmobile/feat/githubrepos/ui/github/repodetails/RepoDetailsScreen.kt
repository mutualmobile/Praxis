package com.mutualmobile.feat.githubrepos.ui.github.repodetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.praxis.commonui.material.CommonTopAppBar
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import kotlinx.datetime.*
import java.time.format.DateTimeFormatter
import kotlin.time.DurationUnit

const val DATE_TIME_DETAILS = "MMM d yyyy hh:mm a"

@Composable
fun RepoDetailsScreen(uiRepo: UIRepo) {
  Scaffold(
    backgroundColor = PraxisTheme.colors.uiBackground,
    contentColor = PraxisTheme.colors.textSecondary,
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    topBar = {
      CommonTopAppBar(uiRepo.fullName)
    }) {
    PraxisSurface(
      color = PraxisTheme.colors.uiBackground,
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
    ) {
      Column(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
      ) {
        Text(
          text = "Language: ${if (uiRepo.language.isNullOrBlank()) "Empty" else uiRepo.language}",
          style = MaterialTheme.typography.body1,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
        )
        Text(
          text = "Created: ${
            uiRepo.createDate.toInstant()
              .toLocalDateTime(TimeZone.currentSystemDefault())
              .toJavaLocalDateTime()
              .format(DateTimeFormatter.ofPattern(DATE_TIME_DETAILS))
          }",
          style = MaterialTheme.typography.body1,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
        )
        Text(
          text = (Clock.System.now() - uiRepo.createDate.toInstant()).toInt(DurationUnit.SECONDS)
            .let {
              return@let "Duration: ~${
                listOf(
                  ":%02d".format(it % 60),
                  ":%02d".format((it % 3600) / 60),
                  "%d".format((it % 86400) / 3600),
                  "%d days ".format((it % 31536000) / 86400),
                  "%d years ".format(it / 31536000)
                )
                  .filter { t -> !t.contains("0 days") && !t.contains("0 years") }
                  .reversed()
                  .joinToString("") { t ->
                    when (t) {
                      "1 days" -> "1 day"
                      "1 years" -> "1 year"
                      else -> t
                    }
                  }
              }"
            },
          style = MaterialTheme.typography.body2,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          modifier = Modifier
            .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
        )
      }
    }
  }
}