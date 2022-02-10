package com.mutualmobile.uirandomusers.randomusers

import com.mutualmobile.praxis.domain.mappers.UiModelMapper
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer.RandomUser
import com.mutualmobile.praxis.domain.model.randomuser.DomainLayer.RandomUserResponse
import com.mutualmobile.uirandomusers.model.Name
import com.mutualmobile.uirandomusers.model.Picture
import com.mutualmobile.uirandomusers.model.UiLayer
import javax.inject.Inject

class RandomUsersModelUiMapper @Inject constructor(): UiModelMapper<RandomUserResponse, UiLayer.RandomUserResponse> {
  override fun mapToPresentation(model: RandomUserResponse): UiLayer.RandomUserResponse {
    return UiLayer.RandomUserResponse(
        com.mutualmobile.uirandomusers.model.Info(
            model.info?.page, model.info?.results,
            model.info?.seed, model.info?.version,
        ), results = model.results?.mapToPresentation()
    )
  }

  override fun mapToDomain(modelItem: UiLayer.RandomUserResponse): RandomUserResponse {
    TODO("Not yet implemented")
  }
}

fun List<RandomUser>.mapToPresentation(): List<UiLayer.RandomUser> {
  return this.map { result ->
    UiLayer.RandomUser(
        name = Name(result.name?.first,result.name?.last,result.name?.title),
        gender = result.gender,
        email = result.email,
        phone=result.phone,
        dob = com.mutualmobile.uirandomusers.model.Dob(result.dob?.age, result.dob?.date),
        location = com.mutualmobile.uirandomusers.model.Location(
            city = result.location?.city,
            country = result.location?.country, state = result.location?.state,
            postcode = result.location?.postcode
        ), picture = Picture(medium = result.picture?.medium)
    )
  }
}
