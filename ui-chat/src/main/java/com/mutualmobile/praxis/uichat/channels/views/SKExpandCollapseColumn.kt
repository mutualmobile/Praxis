package com.mutualmobile.praxis.uichat.channels.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mutualmobile.praxis.commonui.theme.PraxisColorProvider
import com.mutualmobile.praxis.commonui.theme.PraxisTypography
import com.mutualmobile.praxis.commonui.reusable.PraxisListItem
import com.mutualmobile.praxis.uichat.channels.data.ExpandCollapseModel
import com.mutualmobile.praxis.uichat.models.ChatPresentation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SKExpandCollapseColumn(
  expandCollapseModel: ExpandCollapseModel,
  onItemClick: (ChatPresentation.PraxisChannel) -> Unit = {},
  onExpandCollapse: (isChecked: Boolean) -> Unit,
  channels: List<ChatPresentation.PraxisChannel>,
) {
  Column(
    Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
  ) {
    Row(
      Modifier
        .fillMaxWidth()
        .clickable {
          onExpandCollapse(!expandCollapseModel.isOpen)
        },
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = expandCollapseModel.title,
        style = PraxisTypography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
        modifier = Modifier.weight(1f)
      )
      AddButton(expandCollapseModel)
      ToggleButton(expandCollapseModel, onExpandCollapse)
    }
    ChannelsList(expandCollapseModel, onItemClick, channels)
    Divider(color = PraxisColorProvider.colors.lineColor, thickness = 0.5.dp)
  }
}

@Composable
private fun ColumnScope.ChannelsList(
  expandCollapseModel: ExpandCollapseModel,
  onItemClick: (ChatPresentation.PraxisChannel) -> Unit = {},
  channels: List<ChatPresentation.PraxisChannel>
) {
  AnimatedVisibility(visible = expandCollapseModel.isOpen) {
    Column {
      repeat(channels.size) {
        val slackChannel = channels[it]
        PraxisListItem(
          icon = if (slackChannel.isPrivate) Icons.Default.Lock else Icons.Default.MailOutline,
          title = "${slackChannel.name}",
          onItemClick = {
            onItemClick(slackChannel)
          }
        )
      }
    }
  }
}

@Composable
private fun AddButton(
  expandCollapseModel: ExpandCollapseModel,
) {
  if (expandCollapseModel.needsPlusButton) {
    IconButton(onClick = {

    }) {
      Icon(
        imageVector = Icons.Default.Add,
        contentDescription = null,
        tint = PraxisColorProvider.colors.lineColor
      )
    }
  }

}

@Composable
private fun ToggleButton(
  expandCollapseModel: ExpandCollapseModel,
  onExpandCollapse: (isChecked: Boolean) -> Unit
) {
  IconToggleButton(checked = expandCollapseModel.isOpen, onCheckedChange = {
    onExpandCollapse(it)
  }) {
    Icon(
      imageVector = if (expandCollapseModel.isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
      contentDescription = null,
      tint = PraxisColorProvider.colors.lineColor
    )
  }
}