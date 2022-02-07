package com.mutualmobile.praxis.domain.model.randomuser

import com.mutualmobile.praxis.domain.mappers.DomainModel

interface DomainLayer {
  data class RandomUserResponse(
    val info: Info? = null,
    val results: List<RandomUser>? = null
  ) : DomainModel()

  data class RandomUser(
    val cell: String? = null,
    val dob: Dob? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: Id? = null,
    val location: Location? = null,
    val login: Login? = null,
    val name: Name? = null,
    val nat: String? = null,
    val phone: String? = null,
    val picture: Picture? = null,
    val registered: Registered? = null
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
  val city: String? = null,
  val coordinates: Coordinates? = null,
  val country: String? = null,
  val postcode: Any? = null,
  val state: String? = null,
  val street: Street? = null,
  val timezone: Timezone? = null
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
  val large: String? = null,
  val medium: String? = null,
  val thumbnail: String? = null
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

