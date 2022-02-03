package com.mutualmobile.praxis.domain.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.domain.usecases.GetGithubTrendingReposUseCase
import com.mutualmobile.praxis.domain.usecases.UseCaseFetchRandomUsers
import com.mutualmobile.praxis.repository.*
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

  @Provides
  @ViewModelScoped
  fun provideUseCaseFetchRandomUsers(userRepository: UserRepository) =
    UseCaseFetchRandomUsers(userRepository)
}