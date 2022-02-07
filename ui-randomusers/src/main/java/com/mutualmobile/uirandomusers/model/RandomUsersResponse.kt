package com.mutualmobile.uirandomusers.model

import com.mutualmobile.praxis.domain.mappers.UIModel

interface UiLayer {
  data class RandomUserResponse(
    val info: Info?,
    val results: List<RandomUser>?
  ) : UIModel()

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
  ) : UIModel()
}

data class Info(
  val page: Int?,
  val results: Int?,
  val seed: String?,
  val version: String?
) : UIModel()

data class Dob(
  val age: Int?,
  val date: String?
) : UIModel()

data class Id(
  val name: String?,
  val value: Any?
) : UIModel()

data class Location(
  val city: String? = null,
  val coordinates: Coordinates? = null,
  val country: String? = null,
  val postcode: Any? = null,
  val state: String? = null,
  val street: Street? = null,
  val timezone: Timezone? = null
) : UIModel()

data class Login(
  val md5: String?,
  val password: String?,
  val salt: String?,
  val sha1: String?,
  val sha256: String?,
  val username: String?,
  val uuid: String?
) : UIModel()

data class Name(
  val first: String?,
  val last: String?,
  val title: String?
) : UIModel()

data class Picture(
  val large: String? = null,
  val medium: String? = null,
  val thumbnail: String? = null
) : UIModel()

data class Registered(
  val age: Int?,
  val date: String?
) : UIModel()

data class Coordinates(
  val latitude: String?,
  val longitude: String?
) : UIModel()

data class Street(
  val name: String?,
  val number: Int?
) : UIModel()

data class Timezone(
  val description: String?,
  val offset: String?
) : UIModel()

