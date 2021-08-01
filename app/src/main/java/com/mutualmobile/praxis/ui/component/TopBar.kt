package com.mutualmobile.praxis.ui.component

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mutualmobile.praxis.R

/**
 * Created by Rooparsh Kalia on 31/07/21
 */

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
            title = {
                Text(stringResource(id = R.string.app_name))
            }
    )
}

@Composable
@Preview
fun TopBarPreview() {
    TopBar()
}