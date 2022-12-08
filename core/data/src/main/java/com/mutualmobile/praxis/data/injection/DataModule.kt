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
import javax.inject.Singleton
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
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