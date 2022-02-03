package com.mutualmobile.uirandomusers.model

import com.mutualmobile.praxis.domain.mappers.UIModel

interface UiLayer {
  data class RandomUserResponse(
    val info: Info?,
    val results: List<RandomUser>?
  ) : UIModel()

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
  val city: String?,
  val coordinates: Coordinates?,
  val country: String?,
  val postcode: Any?,
  val state: String?,
  val street: Street?,
  val timezone: Timezone?
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
  val large: String?,
  val medium: String?,
  val thumbnail: String?
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

