package com.mutualmobile.feat.jokes.ui.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.mutualmobile.base.BaseComposeTest
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.utils.enqueueResponse
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class DashboardShould : BaseComposeTest() {

    @Test
    fun display_demo_jokes() {
        mockWebServer.enqueueResponse("jokes_response.json", 200)
        composeTestRule.setContent {
            PraxisTheme {
                Dashboard()
            }
        }
        with(composeTestRule) {
            onNodeWithText("Chuck Norris can believe it's not butter.").assertIsDisplayed()
        }
    }

    @Test
    fun display_jokeDetailScreen_onClick_of_joke() {
        with(composeTestRule) {
            onNodeWithText("Email").performTextInput("shubham@gmail.com")
            onNodeWithText("Password").performTextInput("testpassword")
            onNodeWithText("Login").performClick()
            onNodeWithText("Chuck Norris Random Joke Generator").assertIsDisplayed()
            mockWebServer.enqueueResponse("jokes_response.json", 200)
            onNodeWithText("Chuck Norris can believe it's not butter.").performClick()
            onNodeWithText("Joke Detail").assertIsDisplayed()
        }
    }

}