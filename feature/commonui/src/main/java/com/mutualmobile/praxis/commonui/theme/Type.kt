package com.mutualmobile.praxis.commonui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mutualmobile.praxis.commonui.R

// Set of Material typography styles to start with

val praxisFontFamily =
  FontFamily(
    Font(R.font.lato_bold, weight = FontWeight.Bold),
    Font(R.font.lato_light, weight = FontWeight.Light),
    Font(R.font.lato_regular)
  )

val PraxisTypography = Typography(
  defaultFontFamily = praxisFontFamily,
  body1 = TextStyle(
    fontFamily = praxisFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),
  button = TextStyle(
    fontFamily = praxisFontFamily,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
  ),
  caption = TextStyle(
    fontFamily = praxisFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
  )


)