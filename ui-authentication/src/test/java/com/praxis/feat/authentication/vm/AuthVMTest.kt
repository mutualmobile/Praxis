package com.praxis.feat.authentication.vm

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.mutualmobile.praxis.navigator.AbsComposeNavigator
import com.praxis.feat.authentication.ui.model.LoginForm
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class AuthVMTest {

  @MockK
  lateinit var navigatorAbs: AbsComposeNavigator

  @MockK
  private lateinit var savedStateHandle: SavedStateHandle

  private lateinit var authVM: AuthVM

  @Before
  fun setUp() {
    MockKAnnotations.init(this, true)
    Dispatchers.setMain(StandardTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `test that login now fails with validation exception`() {
    runTest {
      launch {
        coEvery {
          navigatorAbs.observeResult<String>(any())
        } returns emptyFlow()

        coEvery {
          savedStateHandle.get<String>(any())
        } returns ""

        authVM = AuthVM(savedStateHandle, navigatorAbs)

        authVM.formUiState.test {
          assert(awaitItem() is AuthVM.UiState.Empty)
          authVM.loginNow()
          assert(awaitItem() is AuthVM.UiState.LoadingState)
          assert(awaitItem() is AuthVM.UiState.ErrorState)
          assert(awaitItem() is AuthVM.UiState.Empty)
          awaitComplete()
        }
      }
    }
  }

  @Test
  fun `test that loginNow() completes with no exception`() {
    runTest {
      launch {
        coEvery {
          navigatorAbs.observeResult<String>(any())
        } returns emptyFlow()

        coEvery {
          savedStateHandle.get<String>(any())
        } returns ""

        authVM = AuthVM(savedStateHandle, navigatorAbs)
        authVM.credentials.value = LoginForm("anmol@gmail.com", "sdkfkjkjfdsjkfds")

        authVM.formUiState.test {
          assert(awaitItem() is AuthVM.UiState.Empty)
          authVM.loginNow()
          assert(awaitItem() is AuthVM.UiState.LoadingState)
          assert(awaitItem() is AuthVM.UiState.SuccessState)
        }
      }
    }
  }
}