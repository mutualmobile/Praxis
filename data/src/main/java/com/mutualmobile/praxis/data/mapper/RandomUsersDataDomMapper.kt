package com.mutualmobile.praxis.data.mapper

import android.util.Log
import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer
import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer.Picture
import com.mutualmobile.praxis.data.remote.model.randomuser.DataLayer.Result
import com.mutualmobile.praxis.domain.model.randomuser.Dob
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer.RandomUser
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer.RandomUserResponse
import com.mutualmobile.praxis.domain.model.randomuser.Info
import com.mutualmobile.praxis.domain.model.randomuser.Location
import com.mutualmobile.praxis.domain.model.randomuser.Name
import javax.inject.Inject

class RandomUsersDataDomMapper @Inject constructor() : EntityMapper<RandomUserResponse, DataLayer.RandomUserResponse> {
  override fun mapToDomain(entity: DataLayer.RandomUserResponse): RandomUserResponse {
    return RandomUserResponse(
        Info(
            entity.info?.page, entity.info?.results,
            entity.info?.seed, entity.info?.version,
        ), results = entity.results?.mapToDomain()
    )
  }

  override fun mapToData(model: RandomUserResponse): DataLayer.RandomUserResponse {
    return DataLayer.RandomUserResponse(
        DataLayer.Info(
            model.info?.page, model.info?.results,
            model.info?.seed, model.info?.version,
        ), results = model.results?.mapToData()
    )
  }
}

fun List<Result>.mapToDomain(): List<RandomUser> {
  return this.map { result ->
    RandomUser(
        name = Name(result.name?.first, result.name?.last, result.name?.title),
        gender = result.gender,
        phone=result.phone,
        email = result.email, dob = Dob(result.dob?.age, result.dob?.date), location = Location(
        city = result.location?.city,
        country = result.location?.country, state = result.location?.state,
        postcode = result.location?.postcode
    ), picture = com.mutualmobile.praxis.domain.model.randomuser.Picture(
        medium = result.picture?.medium
    )
    )
  }
}

fun List<RandomUser>.mapToData(): List<Result> {
  return this.map { result ->
    Result(
        name = DataLayer.Name(result.name?.first, result.name?.last, result.name?.title),
        gender = result.gender,
        phone=result.phone,
        email = result.email, dob = DataLayer.Dob(result.dob?.age, result.dob?.date),
        location = DataLayer.Location(
            city = result.location?.city,
            country = result.location?.country, state = result.location?.state,
            postcode = result.location?.postcode
        ), picture = Picture(medium = result.picture?.medium)
    )
  }
}

