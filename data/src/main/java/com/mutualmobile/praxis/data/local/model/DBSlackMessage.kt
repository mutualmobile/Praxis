package com.mutualmobile.praxis.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mutualmobile.praxis.data.mapper.DataModel

@Entity(tableName = "slackMessage")
data class DBPraxisMessage(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "from") val userId: String,
    @ColumnInfo(name = "createdBy") val createdBy: String,
    @ColumnInfo(name = "createdDate") val createdDate: Long,
    @ColumnInfo(name = "modifiedDate") val modifiedDate: Long,
) : DataModel()