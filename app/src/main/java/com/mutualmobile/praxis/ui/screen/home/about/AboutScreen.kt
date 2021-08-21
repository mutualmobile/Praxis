package com.mutualmobile.praxis.ui.screen.home.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mutualmobile.praxis.R
import retrofit2.http.Url

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
            .wrapContentSize()
            .background(color = Color.White)
            .then(Modifier.padding(14.dp))
    ) {
        val context = LocalContext.current
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))
        Text(text = stringResource(id = R.string.about),
                style = TextStyle(fontSize = 26.sp), color = MaterialTheme.colors.onSecondary)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))
        Text(text = stringResource(id = R.string.about_text),
                color = MaterialTheme.colors.onSecondary)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20dp)))

        val annotatedText = buildAnnotatedString {
            pushStringAnnotation(tag = "URL",
                    annotation = stringResource(id = R.string.mutualMobileWebLink))
            withStyle(style = SpanStyle(color = Color.Blue,
                    fontWeight = FontWeight.Bold)) {
                append(stringResource(id = R.string.mutualMobile))
            }
            pop()
        }

        ClickableText(text = annotatedText, onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "URL", start = offset,
                    end = offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                        context.startActivity(intent)
                    }
        })
    }
}

@Composable
@Preview
fun PreviewAboutScreen() {
    AboutScreen()
}
