package com.mutualmobile.praxis.ui.onboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mutualmobile.praxis.injection.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class OnBoardViewModel @Inject constructor() : ViewModel() {
  var onBoardMessage = MutableLiveData<String>()

  init {
    onBoardMessage.value = "Hey, this message is from OnBoardViewModel"
  }
}