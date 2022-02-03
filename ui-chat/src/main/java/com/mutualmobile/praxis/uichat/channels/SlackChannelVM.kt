package com.mutualmobile.praxis.uichat.channels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannelType
import com.mutualmobile.praxis.uichat.injection.ChatUiModelMapper
import com.mutualmobile.praxis.uichat.models.ChatPresentation
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PraxisChannelVM @Inject constructor(
  ucFetchChannels: UseCaseFetchChannels,
  @ChatUiModelMapper private val chatPresentationMapper: UiModelMapper<PraxisChannel, ChatPresentation.PraxisChannel>
) : ViewModel() {
  val channels = ucFetchChannels.performStreaming(PraxisChannelType.GROUP).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }


}