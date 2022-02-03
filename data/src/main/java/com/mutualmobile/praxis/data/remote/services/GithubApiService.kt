package com.mutualmobile.praxis.data.remote.services

import com.mutualmobile.praxis.data.remote.model.NETRepoListData
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

  @GET("search/repositories?sort=stars")
  suspend fun getTrendingRepos(
    @Query("q") query: String,
    @Query("page") page: Int,
    @Query("per_page") itemsPerPage: Int
  ): NETRepoListData
}