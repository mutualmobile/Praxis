package com.mutualmobile.praxis.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mutualmobile.praxis.data.local.model.DBPraxisMessage

@Dao
interface PraxisMessageDao {
    @Query("SELECT * FROM slackMessage")
    fun getAll(): List<DBPraxisMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(messages: List<DBPraxisMessage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: DBPraxisMessage)

    // The Int type parameter tells Room to use a PositionalDataSource object.
    @Query("SELECT * FROM slackMessage ORDER BY createdDate DESC")
    fun messagesByDate(): PagingSource<Int, DBPraxisMessage>
}