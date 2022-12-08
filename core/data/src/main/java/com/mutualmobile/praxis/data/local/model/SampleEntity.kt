package com.mutualmobile.praxis.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val SAMPLE_ENTITY_TABLE = "sample_entity_table"

@Entity(tableName = SAMPLE_ENTITY_TABLE)
data class SampleEntity(
  @PrimaryKey @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "cell") val phone: String
)