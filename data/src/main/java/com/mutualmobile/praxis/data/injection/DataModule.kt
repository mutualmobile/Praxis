package com.mutualmobile.praxis.data.injection

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mutualmobile.praxis.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
  @Provides
  @Singleton
  fun providesRoomDB(@ApplicationContext context: Context): RoomDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
  }

  @Provides
  @Singleton
  fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
    return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
  }

}