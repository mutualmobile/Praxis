package com.mutualmobile.praxis

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestBaseApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
abstract class BaseTest {
  lateinit var application: TestBaseApplication

  @Before
  fun setup() {
    application = ApplicationProvider.getApplicationContext() as TestBaseApplication
    injectDagger(application)
  }

  abstract fun injectDagger(applicationBaseApplication: TestBaseApplication)
}