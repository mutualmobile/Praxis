package com.mutualmobile.praxis.uidashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.reusable.PraxisMentionReactionListItem
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.uidashboard.R

@Composable
fun MentionsReactionsUI() {
    PraxisSurface(
        color = PraxisColorProvider.colors.uiBackground,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            MRTopAppBar()
            Column(Modifier.verticalScroll(rememberScrollState())) {
                PraxisMentionReactionListItem(
                    "Anmol Verma mentioned you in a direct message",
                    "https://i.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U",
                    "Anmol",
                    stringResource(R.string.chat_mssg_dummy),
                    "8h"
                )
                Divider(color = PraxisColorProvider.colors.lineColor, thickness = 0.5.dp)
                PraxisMentionReactionListItem(
                    "Hari mentioned you in a direct message",
                    "https://picsum.photos/seed/picsum/200/300",
                    "Hari",
                    stringResource(R.string.chat_mssg_dummy),
                    "4h"
                )
                Divider(color = PraxisColorProvider.colors.lineColor, thickness = 0.5.dp)
                PraxisMentionReactionListItem(
                    "",
                    "https://picsum.photos/200/300?grayscale",
                    "Google Calendar",
                    stringResource(R.string.chat_mssg_dummy),
                    "Feb 9"
                )
            }
        }
    }
}

@Composable
private fun MRTopAppBar() {
    PraxisSurfaceAppBar(
        title = {
            Text(
                text = "Mentions & Reactions",
                style = PraxisTypography.h5.copy(color = Color.White, fontWeight = FontWeight.Bold)
            )
        },
        backgroundColor = PraxisColorProvider.colors.appBarColor,
    )
}
