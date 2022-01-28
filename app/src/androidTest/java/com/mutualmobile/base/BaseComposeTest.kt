package com.mutualmobile.base

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mutualmobile.praxis.root.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import javax.inject.Inject
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}