package com.mutualmobile.praxis.data.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.mutualmobile.praxis.data.local.PraxisDatabase
import com.mutualmobile.praxis.data.local.dao.PraxisChannelDao
import com.mutualmobile.praxis.data.local.dao.PraxisMessageDao
import com.mutualmobile.praxis.data.local.dao.PraxisUserDao
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @RepositoryCoroutineContext
    fun provideCoroutineContext() : CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PraxisDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            PraxisDatabase::class.java,
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun providesPraxisMessageDao(slackDatabase: PraxisDatabase): PraxisMessageDao =
        slackDatabase.slackMessageDao()

    @Provides
    @Singleton
    fun provideChannelDao(slackDatabase: PraxisDatabase): PraxisChannelDao =
        slackDatabase.slackChannelDao()

    @Provides
    @Singleton
    fun provideUserDao(slackDatabase: PraxisDatabase): PraxisUserDao = slackDatabase.slackUserDao()
}