package com.mutualmobile.praxis.feat.authentication.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.mutualmobile.praxis.base.BaseComposeTest
import org.junit.Test

class AuthenticationUIShould: BaseComposeTest() {

    @Test
    fun display_emailErrorSnackBar_onClick_of_loginBtn_with_empty_credentials() {
        with(composeTestRule) {
            onNodeWithText("Login").performClick()
            onNodeWithText("Email is not valid").assertIsDisplayed()
        }
    }

    @Test
    fun display_passwordErrorSnackBar_onClick_of_loginBtn_with_empty_password_only() {
        with(composeTestRule) {
            onNodeWithText("Email").performTextInput("shubham@gmail.com")
            onNodeWithText("Login").performClick()
            onNodeWithText("Password should be at least 6 characters long").assertIsDisplayed()
        }
    }

    @Test
    fun display_emailErrorSnackBar_onClick_of_loginBtn_with_empty_email_only() {
        with(composeTestRule) {
            onNodeWithText("Password").performTextInput("testpassword")
            onNodeWithText("Login").performClick()
            onNodeWithText("Email is not valid").assertIsDisplayed()
        }
    }

}