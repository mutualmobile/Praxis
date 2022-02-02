package com.mutualmobile.praxis.domain.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.mutualmobile.praxis.domain.repository.ChannelsRepository
import com.mutualmobile.praxis.domain.repository.IGithubRepo
import com.mutualmobile.praxis.domain.repository.IJokesRepo
import com.mutualmobile.praxis.domain.repository.MessagesRepository
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import com.mutualmobile.praxis.uichat.UseCaseSendMessage
import com.mutualmobile.praxis.uichat.channels.UseCaseFetchChannels
import com.mutualmobile.praxis.uichat.chat.UseCaseFetchMessages

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchChannels(channelsRepository: ChannelsRepository) =
    UseCaseFetchChannels(channelsRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchMessages(messagesRepository: MessagesRepository) =
    UseCaseFetchMessages(messagesRepository)

  @Provides
  @ViewModelScoped
  fun provideUseCaseSendMessage(messagesRepository: MessagesRepository) =
    UseCaseSendMessage(messagesRepository)

  @Provides
  @ViewModelScoped
  fun provideGetFiveRandomJokes(repo: IJokesRepo): GetFiveRandomJokesUseCase {
    return GetFiveRandomJokesUseCase(repo)
  }

  @Provides
  @ViewModelScoped
  fun provideGithubTrendingRepos(repo: IGithubRepo): GetGithubTrendingReposUseCase {
    return GetGithubTrendingReposUseCase(repo)
  }
}