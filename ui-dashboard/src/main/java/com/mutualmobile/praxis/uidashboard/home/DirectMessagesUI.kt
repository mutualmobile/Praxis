package com.mutualmobile.praxis.uidashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.material.PraxisSurfaceAppBar
import com.mutualmobile.praxis.commonui.reusable.PraxisDMListItem
import com.mutualmobile.praxis.commonui.reusable.PraxisImageBox
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisSurface
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.uidashboard.R

@Composable
fun DirectMessagesUI() {
    PraxisSurface(
        color = PraxisColorProvider.colors.uiBackground,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(Modifier.verticalScroll(rememberScrollState())) {
            DMTopAppBar()
            JumpToText()
            PraxisDMListItem(
                "https://i.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U",
                "Anmol",
                stringResource(R.string.chat_mssg_dummy),
                "8h"
            )
            PraxisDMListItem(
                "https://picsum.photos/seed/picsum/200/300",
                "Hari",
                stringResource(R.string.chat_mssg_dummy),
                "4h"
            )
            PraxisDMListItem(
                "https://picsum.photos/200/300?grayscale",
                "Google Calendar",
                stringResource(R.string.chat_mssg_dummy),
                "Feb 9"
            )
            PraxisDMListItem(
                "https://i.picsum.photos/id/0/5616/3744.jpg?hmac=3GAAioiQziMGEtLbfrdbcoenXoWAW-zlyEAMkfEdBzQ",
                "Kristin Moore",
                stringResource(R.string.chat_mssg_dummy),
                "Feb 9"
            )
            PraxisDMListItem(
                "https://i.picsum.photos/id/100/2500/1656.jpg?hmac=gWyN-7ZB32rkAjMhKXQgdHOIBRHyTSgzuOK6U0vXb1w",
                "Pushpal Roy",
                stringResource(R.string.chat_mssg_dummy),
                "Jan 8"
            )
            PraxisDMListItem(
                "https://i.picsum.photos/id/1025/4951/3301.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y",
                "Kamal Kishore",
                stringResource(R.string.chat_mssg_dummy),
                "Jan 1"
            )
        }
    }
}


@Composable
fun UserHeader1() {
    Row(Modifier.padding(12.dp)) {
        PraxisImageBox(
            Modifier.size(72.dp),
            "https://ca.slack-edge.com/T02TLUWLZ-U2ZG961MW-176c142f9265-512"
        )
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                text = "Anmol Verma",
                style = PraxisTypography.h6.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = "Active",
                style = PraxisTypography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = PraxisColorProvider.colors.textPrimary.copy(alpha = 0.4f)
                )
            )
        }
    }
}


@Composable
fun DMTopAppBar() {
    PraxisSurfaceAppBar(
        title = {
            Text(
                text = "Direct Messages",
                style = PraxisTypography.h5.copy(color = Color.White, fontWeight = FontWeight.Bold)
            )
        },
        backgroundColor = PraxisColorProvider.colors.appBarColor,
    )
}
