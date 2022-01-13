package com.praxis.feat.authentication.vm

import androidx.lifecycle.SavedStateHandle
import com.mutualmobile.praxis.navigator.Navigator
import com.mutualmobile.praxis.navigator.PraxisNavigator
import com.praxis.feat.authentication.MainCoroutineRule
import com.praxis.authentication.login.LoginVM
import com.praxis.authentication.common.model.LoginForm
import com.praxis.authentication.login.LoginVM.UiState.Error
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginVMTest {

  private var navigator: Navigator = PraxisNavigator()
  private lateinit var savedStateHandle: SavedStateHandle
  private lateinit var loginVM: LoginVM

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

      loginVM = LoginVM(savedStateHandle, navigator)

      assert(loginVM.uiState.value is LoginVM.UiState.Empty)
      loginVM.login()
      assert(loginVM.uiState.value is Error)
    }
  }

  @Test
  fun `test that loginNow() completes with no exception`() {
    runBlocking {

      coEvery {
        savedStateHandle.get<String>(any())
      } returns ""

      loginVM = LoginVM(savedStateHandle, navigator)
      loginVM.credentials.value = LoginForm("anmol@gmail.com","sdkfkjkjfdsjkfds")
      assert(loginVM.uiState.value is LoginVM.UiState.Empty)
      loginVM.login()
      assert(loginVM.uiState.value is LoginVM.UiState.SuccessWithData)
    }
  }

  @Test
  fun `navigateForgotPassword how ?`() {
    TODO("how do we test navigation controller in unit test ?")
  }
}