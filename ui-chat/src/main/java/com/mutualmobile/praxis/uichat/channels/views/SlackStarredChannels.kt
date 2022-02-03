package com.mutualmobile.praxis.uichat.channels.views

import com.mutualmobile.praxis.uichat.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.uichat.channels.PraxisChannelVM
import com.mutualmobile.praxis.uichat.channels.data.ExpandCollapseModel
import com.mutualmobile.praxis.uichat.models.ChatPresentation

@Composable
fun PraxisStarredChannels(
  onItemClick: (ChatPresentation.PraxisChannel) -> Unit = {},
  channelVM: PraxisChannelVM = hiltViewModel()
) {
  val recent = stringResource(R.string.starred)
  val channels by channelVM.channels.collectAsState(initial = emptyList())
  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
        1, recent,
        needsPlusButton = false,
        isOpen = false
      )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel, onItemClick, {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  }, channels)
}