package com.mutualmobile.praxis.ui.home

import androidx.lifecycle.MutableLiveData
import com.mutualmobile.praxis.data.model.JokeListResponse
import com.mutualmobile.praxis.data.services.ApiService
import com.mutualmobile.praxis.injection.scope.ActivityScope
import com.mutualmobile.praxis.ui.base.BaseViewModel
import com.mutualmobile.praxis.ui.base.navigator.Navigator
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor() : BaseViewModel() {
  @Inject
  lateinit var service: ApiService
  @Inject
  lateinit var navigator: Navigator

  var dataLoading: MutableLiveData<Boolean> = MutableLiveData()
  var dataJokes: MutableLiveData<JokeListResponse> = MutableLiveData()

   fun loadData() {
    dataLoading.postValue(true)
    dataLoading.postValue(true)
     workerScope.launch {
       try{
        val response = service.getFiveRandomJokes().await()
         dataJokes.postValue(response)
      }catch (e:Exception){
         Timber.e(e)
       }finally {
         dataLoading.postValue(false)
       }
      }
    }
}