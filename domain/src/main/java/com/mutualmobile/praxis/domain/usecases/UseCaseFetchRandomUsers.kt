package com.mutualmobile.praxis.domain.usecases

import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer
import com.mutualmobile.praxis.repository.UserRepository

class UseCaseFetchRandomUsers(
  private val userRepository: UserRepository,
) :
  BaseUseCase<DomainLayer.RandomUserResponse, Int> {
  override suspend fun perform(param: Int): DomainLayer.RandomUserResponse {
    return userRepository.fetchUsers(param)
  }
}