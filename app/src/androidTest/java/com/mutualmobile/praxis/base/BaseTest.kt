package com.mutualmobile.praxis.base

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mutualmobile.praxis.root.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

}