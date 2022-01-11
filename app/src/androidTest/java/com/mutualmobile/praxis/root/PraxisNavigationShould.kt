package com.mutualmobile.praxis.root

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.mutualmobile.praxis.base.BaseComposeTest
import org.junit.Test

class PraxisNavigationShould : BaseComposeTest() {

    @Test
    fun check_if_all_items_are_displayed_correctly() {
        with(composeTestRule) {
            onNodeWithText("Authentication").assertIsDisplayed()
            onNodeWithContentDescription("Logo").assertIsDisplayed()
            onNodeWithText("Email").assertIsDisplayed()
            onNodeWithContentDescription("Email").assertIsDisplayed()
            onNodeWithText("Password").assertIsDisplayed()
            onNodeWithContentDescription("Password").assertIsDisplayed()
            onNodeWithText("Login").assertIsDisplayed()
            onNodeWithText("Forgot Password?").assertIsDisplayed()
        }
    }

    @Test
    fun navigate_to_forgotPasswordScreen_onClick_of_forgotPassword_text() {
        with(composeTestRule) {
            onNodeWithText("Forgot Password?").performClick()
            onNodeWithText("Forgot Password").assertIsDisplayed()
        }
    }

    @Test
    fun navigate_to_dashboard_on_correct_credentials() {
        with(composeTestRule) {
            onNodeWithText("Email").performTextInput("shubham@gmail.com")
            onNodeWithText("Password").performTextInput("testpassword")
            onNodeWithText("Login").performClick()
            onNodeWithText("Chuck Norris Random Joke Generator").assertIsDisplayed()
        }
    }
}