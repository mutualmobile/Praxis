package com.mutualmobile.praxis.repository

import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer

interface UserRepository {
  suspend fun fetchUsers(numOfResults: Int): DomainLayer.RandomUserResponse
  suspend fun saveUsers(users: List<DomainLayer.RandomUser>)
}