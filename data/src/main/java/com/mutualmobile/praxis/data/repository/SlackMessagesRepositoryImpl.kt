package com.mutualmobile.praxis.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mutualmobile.praxis.data.injection.RepositoryCoroutineContext
import com.mutualmobile.praxis.data.local.dao.PraxisMessageDao
import com.mutualmobile.praxis.data.local.model.DBPraxisMessage
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import com.mutualmobile.praxis.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PraxisMessagesRepositoryImpl @Inject constructor(
  private val slackMessageDao: PraxisMessageDao,
  private val entityMapper: EntityMapper<PraxisMessage, DBPraxisMessage>,
  @RepositoryCoroutineContext private val coroutineContext: CoroutineContext
) :
  MessagesRepository {
  private val chatPager = Pager(PagingConfig(pageSize = 20)) {
    slackMessageDao.messagesByDate()
  }

  override fun fetchMessages(params: PraxisChannel?): Flow<PagingData<PraxisMessage>> {
    return chatPager.flow.map { messages ->
      messages.map { message ->
        entityMapper.mapToDomain(message)
      }
    }
  }

  override suspend fun sendMessage(params: PraxisMessage) {
    withContext(coroutineContext) {
      slackMessageDao.insert(entityMapper.mapToData(params))
    }
  }
}