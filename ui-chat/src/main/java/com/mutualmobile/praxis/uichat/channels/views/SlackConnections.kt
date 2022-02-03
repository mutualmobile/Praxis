package com.mutualmobile.praxis.uichat.channels

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.uichat.channels.data.ExpandCollapseModel
import com.mutualmobile.praxis.uichat.channels.views.SKExpandCollapseColumn
import com.mutualmobile.praxis.uichat.R
import com.mutualmobile.praxis.uichat.models.ChatPresentation

@Composable
fun PraxisConnections(
  onItemClick: (ChatPresentation.PraxisChannel) -> Unit = {},
  channelVM: PraxisChannelVM = hiltViewModel()
) {
  val recent = stringResource(R.string.connections)
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
