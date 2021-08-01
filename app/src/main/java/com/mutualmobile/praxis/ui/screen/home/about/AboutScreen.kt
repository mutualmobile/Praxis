package com.mutualmobile.praxis.ui.screen.home.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mutualmobile.praxis.R

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

@Composable
fun AboutScreen(viewModel: AboutVM) {
    AboutScreen()
}

@Composable
fun AboutScreen() {
    Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))
        Text(text = stringResource(id = R.string.about),
                style = TextStyle(fontSize = 26.sp), color = MaterialTheme.colors.onSecondary)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))
        Text(text = stringResource(id = R.string.about_text),
                color = MaterialTheme.colors.onSecondary)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))

        with(AnnotatedString.Builder()) {
            append(stringResource(id = R.string.mutualMobile))
            addStringAnnotation(
                    tag = "URL",
                    annotation = stringResource(id = R.string.mutualMobileWebLink),
                    start = 0,
                    end = stringResource(id = R.string.mutualMobile).length
            )
        }
    }
}

@Composable
@Preview
fun PreviewAboutScreen() {
    AboutScreen()
}
