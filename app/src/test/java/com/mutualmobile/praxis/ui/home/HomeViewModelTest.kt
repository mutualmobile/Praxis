package com.mutualmobile.praxis.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mutualmobile.praxis.ImmediateSchedulersRule
import com.mutualmobile.praxis.data.model.Joke
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.services.CoroutineApiService
import com.mutualmobile.praxis.data.services.RxApiService
import com.mutualmobile.praxis.getOrAwaitValue
import com.mutualmobile.praxis.repo.JokeRepo
import com.mutualmobile.praxis.utils.IRxSchedulers
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class HomeViewModelTest {

  @get:Rule
  val immediateSchedulersRule = ImmediateSchedulersRule()

  @get: Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @Mock
  lateinit var rxApiService: RxApiService

  @Mock
  lateinit var coroutineApiService: CoroutineApiService

  val schedulers = object : IRxSchedulers {
    override fun main() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
  }

  @ExperimentalCoroutinesApi
  private val dispatcher = TestCoroutineDispatcher()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    Dispatchers.setMain(dispatcher)
  }

  @Test
  fun isFetchDataWorksWithCoroutines() {

    val coroutineApiService = mockk<CoroutineApiService>()

    every { runBlocking { coroutineApiService.getFiveRandomJokes() } } answers {
      Response.success(
          JokeListResponse(
              "success",
              arrayListOf(
                  Joke(1, "boo"),
                  Joke(2, "boo"),
                  Joke(3, "boo"),
                  Joke(4, "boo"),
                  Joke(5, "boo")
              )
          )
      )
    }

    val jokeRepo = JokeRepo(coroutineApiService, rxApiService)
    val homeViewModel = HomeViewModel(jokeRepo, schedulers)

    assertThat(null, `is`(homeViewModel.dataLoading.value))

    runBlocking {
      homeViewModel.loadDataCoroutine()
    }

    val data = homeViewModel.dataLoading.getOrAwaitValue()

    assertThat(false, `is`(data))

  }

  @Test
  fun isFetchDataWithRx() {

    val rxApiService = mockk<RxApiService>()

    every { rxApiService.getFiveRandomJokes() } answers {
      Single.just(
          JokeListResponse(
              "success",
              arrayListOf(
                  Joke(1, "boo"),
                  Joke(2, "boo"),
                  Joke(3, "boo"),
                  Joke(4, "boo"),
                  Joke(5, "boo")
              )
          )
      )
    }

    val jokeRepo = JokeRepo(coroutineApiService, rxApiService)
    val homeViewModel = HomeViewModel(jokeRepo, schedulers)

    assertThat(null, `is`(homeViewModel.dataLoading.value))

    homeViewModel.loadDataRx()

    val data = homeViewModel.dataLoading.getOrAwaitValue()

    assertThat(false, `is`(data))

  }


}