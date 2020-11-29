// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.data.source.remote

import com.example.codingsample.data.UserResult
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("/public-api/users")
    fun getUser(): Call<UserResult>
}