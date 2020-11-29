package com.example.codingsample.data

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("code") val code : Int,
    @SerializedName("meta") val meta : Meta,
    @SerializedName("data") val data : List<User>
)

data class User (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("status") val status : String,
    @SerializedName("created_at") val createdAt : String,
    @SerializedName("updated_at") val updatedAt : String
)

data class Meta (

    @SerializedName("pagination") val pagination : Pagination
)

data class Pagination (

    @SerializedName("total") val total : Int,
    @SerializedName("pages") val pages : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("limit") val limit : Int
)