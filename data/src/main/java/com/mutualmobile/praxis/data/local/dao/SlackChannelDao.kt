package com.mutualmobile.praxis.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mutualmobile.praxis.data.local.model.DBPraxisChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface PraxisChannelDao {
  @Query("SELECT * FROM slackChannel")
  fun getAll(): List<DBPraxisChannel>

  @Query("SELECT * FROM slackChannel")
  fun getAllAsFlow(): Flow<List<DBPraxisChannel>>

  @Query("SELECT * FROM slackChannel WHERE uuid IN (:groupIds)")
  fun loadAllByIds(groupIds: Array<String>): List<DBPraxisChannel>

  @Query(
    "SELECT * FROM slackChannel WHERE name LIKE :name"
  )
  fun findByName(name:String): List<DBPraxisChannel>

  @Insert
  fun insertAll( channelDB: List<DBPraxisChannel>)

  @Delete
  fun delete(channelDB: DBPraxisChannel)
}