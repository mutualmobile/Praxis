package com.mutualmobile.praxis.uichat.channels.views

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.uichat.channels.data.ExpandCollapseModel
import com.mutualmobile.praxis.uichat.R
import com.mutualmobile.praxis.uichat.channels.PraxisChannelVM
import com.mutualmobile.praxis.uichat.models.ChatPresentation

@Composable
fun PraxisAllChannels(
  onItemClick: (ChatPresentation.PraxisChannel) -> Unit = {},
  channelVM: PraxisChannelVM = hiltViewModel()
) {
  val recent = stringResource(R.string.channels)
  val channels by channelVM.channels.collectAsState(initial = emptyList())
  var expandCollapseModel by remember {
    mutableStateOf(
      ExpandCollapseModel(
        1, recent,
        needsPlusButton = true,
        isOpen = false
      )
    )
  }
  SKExpandCollapseColumn(expandCollapseModel = expandCollapseModel, onItemClick = onItemClick, {
    expandCollapseModel = expandCollapseModel.copy(isOpen = it)
  },channels)
}