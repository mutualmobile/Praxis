package com.mutualmobile.praxis.data.remote.model.randomuser

import com.google.gson.annotations.SerializedName
import com.mutualmobile.praxis.data.mapper.DataModel

interface DataLayer {
  data class RandomUserResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result>?
  ) : DataModel()

  data class Info(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: Int?,
    @SerializedName("seed")
    val seed: String?,
    @SerializedName("version")
    val version: String?
  ) : DataModel()

  data class Result(
    @SerializedName("cell")
    val cell: String? = null,
    @SerializedName("dob")
    val dob: Dob? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("id")
    val id: Id? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("login")
    val login: Login? = null,
    @SerializedName("name")
    val name: Name? = null,
    @SerializedName("nat")
    val nat: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("picture")
    val picture: Picture? = null,
    @SerializedName("registered")
    val registered: Registered? = null
  ) : DataModel()

  data class Dob(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("date")
    val date: String?
  ) : DataModel()

  data class Id(
    @SerializedName("name")
    val name: String?,
    @SerializedName("value")
    val value: Any?
  ) : DataModel()

  data class Location(
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("coordinates")
    val coordinates: Coordinates? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("postcode")
    val postcode: Any? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("street")
    val street: Street? = null,
    @SerializedName("timezone")
    val timezone: Timezone? = null
  ) : DataModel()

  data class Login(
    @SerializedName("md5")
    val md5: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("salt")
    val salt: String?,
    @SerializedName("sha1")
    val sha1: String?,
    @SerializedName("sha256")
    val sha256: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("uuid")
    val uuid: String?
  ) : DataModel()

  data class Name(
    @SerializedName("first")
    val first: String? = null,
    @SerializedName("last")
    val last: String? = null,
    @SerializedName("title")
    val title: String? = null
  ) : DataModel()

  data class Picture(
    @SerializedName("large")
    val large: String? = null,
    @SerializedName("medium")
    val medium: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null
  ) : DataModel()

  data class Registered(
    @SerializedName("age")
    val age: Int? = null,
    @SerializedName("date")
    val date: String? = null
  ) : DataModel()

  data class Coordinates(
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?
  ) : DataModel()

  data class Street(
    @SerializedName("name")
    val name: String?,
    @SerializedName("number")
    val number: Int?
  ) : DataModel()

  data class Timezone(
    @SerializedName("description")
    val description: String?,
    @SerializedName("offset")
    val offset: String?
  ) : DataModel()
}

