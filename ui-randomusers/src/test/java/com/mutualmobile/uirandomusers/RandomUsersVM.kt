package com.mutualmobile.uirandomusers

import app.cash.turbine.test
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer
import com.mutualmobile.praxis.domain.usecases.UseCaseFetchRandomUsers
import com.mutualmobile.praxis.repository.UserRepository
import com.mutualmobile.uirandomusers.model.UiLayer
import com.mutualmobile.uirandomusers.randomusers.RandomUsersVM
import com.mutualmobile.uirandomusers.randomusers.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class RandomUsersVMShould {
  private lateinit var randomUsersVM: RandomUsersVM

  @MockK
  lateinit var userRepository: UserRepository

  @MockK
  lateinit var uiMapper: UiModelMapper<DomainLayer.RandomUserResponse, UiLayer.RandomUserResponse>

  @Before
  fun beforeEachTest() {
    MockKAnnotations.init(this)
    randomUsersVM = RandomUsersVM(UseCaseFetchRandomUsers(userRepository), uiMapper)
    Dispatchers.setMain(StandardTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `always have initial state as empty`() {
    runTest {
      randomUsersVM.uiState.test {
        assert(awaitItem() is UiState.Empty)
      }
    }
  }

  @Test
  fun `show a list of users when viewmodel fetchRandomUsers Is Called`() {
    runTest {
      every {
        uiMapper.mapToPresentation(any())
      } returns UiLayer.RandomUserResponse(null, null)

      coEvery {
        userRepository.fetchUsers(any())
      } returns DomainLayer.RandomUserResponse()

      randomUsersVM.uiState.test {
        assert(awaitItem() is UiState.Empty)
        randomUsersVM.fetchRandomUsers()
        assert(awaitItem() is UiState.Loading)
        assert(awaitItem() is UiState.Data<*>)
      }
    }
  }

  @Test
  fun `failed with exception when viewmodel fetchRandomUsers Is Called`() {
    runTest {
      randomUsersVM.uiState.test {
        assert(awaitItem() is UiState.Empty)
        randomUsersVM.fetchRandomUsers()
        assert(awaitItem() is UiState.Loading)
        assert(awaitItem() is UiState.Error)
      }
    }
  }
}