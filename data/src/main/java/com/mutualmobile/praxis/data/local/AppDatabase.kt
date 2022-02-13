package com.mutualmobile.praxis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mutualmobile.praxis.data.local.model.SampleEntity

@Database(entities = [SampleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase()

