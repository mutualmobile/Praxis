package com.praxis.feat.authentication.vm

import androidx.lifecycle.SavedStateHandle
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.PraxisNavigator
import com.praxis.feat.authentication.MainCoroutineRule
import com.praxis.feat.authentication.ui.model.LoginForm
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthVMTest {

  private var navigator: Navigator = PraxisNavigator()
  private lateinit var savedStateHandle: SavedStateHandle
  private lateinit var authVM: AuthVM

  @get:Rule
  val coroutineRule = MainCoroutineRule()


  @Before
  fun setUp() {
    savedStateHandle = mockk()
  }

  @Test
  fun `test that login now fails with validation exception`() {
    runBlocking {

      coEvery {
        savedStateHandle.get<String>(any())
      } returns ""

      authVM = AuthVM(savedStateHandle, navigator)

      assert(authVM.uiState.value is AuthVM.UiState.Empty)
      authVM.loginNow()
      assert(authVM.uiState.value is AuthVM.UiState.ErrorState)
    }
  }

  @Test
  fun `test that loginNow() completes with no exception`() {
    runBlocking {

      coEvery {
        savedStateHandle.get<String>(any())
      } returns ""

      authVM = AuthVM(savedStateHandle, navigator)
      authVM.credentials.value = LoginForm("anmol@gmail.com","sdkfkjkjfdsjkfds")
      assert(authVM.uiState.value is AuthVM.UiState.Empty)
      authVM.loginNow()
      assert(authVM.uiState.value is AuthVM.UiState.SuccessState)
    }
  }

  @Test
  fun `navigateForgotPassword how ?`() {
    TODO("how do we test navigation controller in unit test ?")
  }
}