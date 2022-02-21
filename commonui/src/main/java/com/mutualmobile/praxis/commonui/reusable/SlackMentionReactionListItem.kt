package com.mutualmobile.praxis.commonui.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PraxisMentionReactionListItem(
    heading: String,
    image: String,
    title: String,
    chat: String,
    time: String,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClick()
            }, verticalAlignment = Alignment.CenterVertically
    ) {


        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = heading,
                    style = PraxisTypography.caption.copy(
                        fontWeight = FontWeight.Light,
                        color = PraxisColorProvider.colors.textPrimary
                    ), modifier = Modifier
                        .padding(4.dp)
                        .padding(start = 48.dp),


                )
                Text(
                    text = time,
                    style = PraxisTypography.subtitle2.copy(
                        fontWeight = FontWeight.Light,
                        color = PraxisColorProvider.colors.textSecondary
                    ), modifier = Modifier.padding(4.dp).fillMaxWidth()

                )
            }

            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {


                PraxisImageBox(Modifier.size(48.dp), imageUrl = image)


                Column {
                    Text(
                        text = title,
                        style = PraxisTypography.subtitle1.copy(
                            fontWeight = FontWeight.Bold,
                            color = PraxisColorProvider.colors.textPrimary
                        ), modifier = Modifier.padding(4.dp)

                    )
                    Text(
                        text = chat,
                        style = PraxisTypography.subtitle2.copy(
                            fontWeight = FontWeight.Normal,
                            color = PraxisColorProvider.colors.textSecondary
                        ), modifier = Modifier.padding(4.dp),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis

                    )
                }


            }




            PraxisImageBox(
                Modifier
                    .fillMaxWidth()
                    .height(228.dp)
                    .padding(start = 4.dp)
                    .padding(start = 48.dp),
                imageUrl = "http://placekitten.com/200/300"
            )


        }


    }
}