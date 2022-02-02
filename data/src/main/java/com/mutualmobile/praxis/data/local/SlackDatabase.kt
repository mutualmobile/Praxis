package com.mutualmobile.praxis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mutualmobile.praxis.data.local.dao.PraxisChannelDao
import com.mutualmobile.praxis.data.local.dao.PraxisMessageDao
import com.mutualmobile.praxis.data.local.dao.PraxisUserDao
import com.mutualmobile.praxis.data.local.model.DBPraxisChannel
import com.mutualmobile.praxis.data.local.model.DBPraxisMessage
import com.mutualmobile.praxis.data.local.model.DBPraxisUser
import com.mutualmobile.praxis.domain.model.channel.PraxisChannelType
import java.util.*


@Database(
    entities = [DBPraxisUser::class, DBPraxisChannel::class, DBPraxisMessage::class],
    version = 1
)
abstract class PraxisDatabase : RoomDatabase() {
    abstract fun slackUserDao(): PraxisUserDao
    abstract fun slackChannelDao(): PraxisChannelDao
    abstract fun slackMessageDao(): PraxisMessageDao
}