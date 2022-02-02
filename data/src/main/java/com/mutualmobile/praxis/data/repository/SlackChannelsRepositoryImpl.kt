package com.mutualmobile.praxis.data.repository

import com.mutualmobile.praxis.data.local.dao.PraxisChannelDao
import com.mutualmobile.praxis.data.local.dao.PraxisMessageDao
import com.mutualmobile.praxis.data.local.model.DBPraxisChannel
import com.mutualmobile.praxis.data.local.model.DBPraxisMessage
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.channel.PraxisChannelType
import com.mutualmobile.praxis.domain.repository.ChannelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PraxisChannelsRepositoryImpl @Inject constructor(
  // create local/network source
  private val slackChannelDao: PraxisChannelDao,
  private val slackMessageDao: PraxisMessageDao,
  private val slackChannelMapper: EntityMapper<PraxisChannel, DBPraxisChannel>,
) :
  ChannelsRepository {

  init {
    // bad ! change, only for testing purposes
    preloadChannels()
    preloadMessages(20)
  }

  override fun fetchChannels(params: PraxisChannelType?): Flow<List<PraxisChannel>> {
    return slackChannelDao.getAllAsFlow()
      .map { list -> list.map { channel -> slackChannelMapper.mapToDomain(channel) } }
  }

  private fun preloadMessages(messagesCount: Int) {
    var days = 36
    repeat(messagesCount) {
      slackMessageDao.insert(
        DBPraxisMessage(
          UUID.randomUUID().toString(),
          "This is a message and test, this is a message and test, this is a message and test.",
          UUID.randomUUID().toString(),
          "Anmol Verma",
          System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days.toLong())-TimeUnit.HOURS.toMillis(it.toLong()),
          System.currentTimeMillis(),
        )
      )
      days += 10
    }
  }

  private fun preloadChannels() {
    val channels = mutableListOf<DBPraxisChannel>()
    repeat(20) {
      channels.add(
        DBPraxisChannel(
          UUID.randomUUID().toString(),
          "prj_jp_compose $it",
          "to explore compose...",
          "Anmol Verma",
          Date().toString(),
          Date().toString(),
          false,
          it % 2 == 0, PraxisChannelType.GROUP, it % 2 == 0
        )
      )
    }
    slackChannelDao.insertAll(channels)
  }
}
