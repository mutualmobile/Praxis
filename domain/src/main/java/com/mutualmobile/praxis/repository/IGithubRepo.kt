package com.mutualmobile.praxis.repository

import androidx.paging.PagingData
import com.mutualmobile.praxis.domain.model.DOMRepo
import kotlinx.coroutines.flow.Flow

interface IGithubRepo {
  suspend fun getTrendingRepos(
    query: String
  ): Flow<PagingData<DOMRepo>>
}