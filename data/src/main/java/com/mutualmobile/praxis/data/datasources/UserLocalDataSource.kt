package com.mutualmobile.praxis.data.datasources

import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer

interface UserLocalDataSource {
  fun saveUsers(randomUsers: List<DomainLayer.RandomUser>)
}
