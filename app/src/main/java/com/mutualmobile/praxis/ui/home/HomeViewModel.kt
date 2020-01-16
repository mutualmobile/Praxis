package com.mutualmobile.praxis.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.repo.JokeRepo
import com.mutualmobile.praxis.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor(
    private val jokeRepo: JokeRepo
) : BaseViewModel() {

  var dataLoading: LiveData<Boolean> = Transformations.map(jokeRepo.repoDataLoading){
    it
  }

  var dataJokes: LiveData<JokeListResponse> = Transformations.map(jokeRepo.repoDataJokes){
    it
  }

  fun loadDataCoroutine() {
    viewModelScope.launch {
      jokeRepo.loadDataCoroutine()
    }
  }

  fun loadDataRx() {
    jokeRepo.loadDataRx()
  }

  override fun onCleared() {
    super.onCleared()
    jokeRepo.onCleared()
  }
}