package com.mutualmobile.praxis.data.injection

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mutualmobile.praxis.data.BuildConfig
import com.mutualmobile.praxis.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import javax.inject.Singleton
import timber.log.Timber

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

  @Provides
  @Singleton
  fun providesKtorClient() = HttpClient(Android) {
    engine {
      connectTimeout = 60_000
      socketTimeout = 60_000
    }

    install(Logging) {
      logger = object : Logger {
        override fun log(message: String) {
          Timber.d("Logger Ktor => $message")
        }
      }
      if (BuildConfig.DEBUG) {
        level = LogLevel.ALL
      }
    }

    install(ResponseObserver) {
      onResponse { response ->
        Timber.d("HTTP status: ${response.status.value}")
      }
    }
  }

}