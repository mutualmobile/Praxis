package com.mutualmobile.praxis.data.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mutualmobile.praxis.data.local.model.DBPraxisChannel
import com.mutualmobile.praxis.data.local.model.DBPraxisMessage
import com.mutualmobile.praxis.data.mapper.EntityMapper
import com.mutualmobile.praxis.data.mapper.PraxisChannelDataDomainMapper
import com.mutualmobile.praxis.data.mapper.PraxisMessageDataDomMapper
import com.mutualmobile.praxis.data.mapper.RandomUsersDataDomMapper
import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer
import com.mutualmobile.praxis.domain.model.channel.PraxisChannel
import com.mutualmobile.praxis.domain.model.message.PraxisMessage
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer.RandomUserResponse
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataMappersModule {

  @Binds
  @Singleton
  abstract fun providePraxisChannelDataDomainMapper(slackChannelDataDomainMapper: PraxisChannelDataDomainMapper): EntityMapper<PraxisChannel, DBPraxisChannel>

  @Binds
  @Singleton
  abstract fun provideRandomUsersDataDomMapper(randomUsersDataDomMapper: RandomUsersDataDomMapper): EntityMapper<RandomUserResponse, DataLayer.RandomUserResponse>

  @Binds
  @Singleton
  abstract fun providePraxisMessageDataDomMapper(slackMessageDataDomMapper: PraxisMessageDataDomMapper): EntityMapper<PraxisMessage, DBPraxisMessage>
}