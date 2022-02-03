package com.mutualmobile.praxis.domain.model.randomuser

import com.mutualmobile.praxis.domain.mappers.DomainModel

interface DomainLayer {
  data class RandomUserResponse(
    val info: Info? = null,
    val results: List<RandomUser>? = null
  ) : DomainModel()

  data class RandomUser(
    val cell: String?,
    val dob: Dob?,
    val email: String?,
    val gender: String?,
    val id: Id?,
    val location: Location?,
    val login: Login?,
    val name: Name?,
    val nat: String?,
    val phone: String?,
    val picture: Picture?,
    val registered: Registered?
  ) : DomainModel()
}

data class Info(
  val page: Int?,
  val results: Int?,
  val seed: String?,
  val version: String?
) : DomainModel()


data class Dob(
  val age: Int?,
  val date: String?
) : DomainModel()

data class Id(
  val name: String?,
  val value: Any?
) : DomainModel()

data class Location(
  val city: String?,
  val coordinates: Coordinates?,
  val country: String?,
  val postcode: Any?,
  val state: String?,
  val street: Street?,
  val timezone: Timezone?
) : DomainModel()

data class Login(
  val md5: String?,
  val password: String?,
  val salt: String?,
  val sha1: String?,
  val sha256: String?,
  val username: String?,
  val uuid: String?
) : DomainModel()

data class Name(
  val first: String?,
  val last: String?,
  val title: String?
) : DomainModel()

data class Picture(
  val large: String?,
  val medium: String?,
  val thumbnail: String?
) : DomainModel()

data class Registered(
  val age: Int?,
  val date: String?
) : DomainModel()

data class Coordinates(
  val latitude: String?,
  val longitude: String?
) : DomainModel()

data class Street(
  val name: String?,
  val number: Int?
) : DomainModel()

data class Timezone(
  val description: String?,
  val offset: String?
) : DomainModel()

