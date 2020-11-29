// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    private const val baseUrl = "https://gorest.co.in/"
    private var apiServices: ApiServices? = null

    val client: ApiServices
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okClient: OkHttpClient.Builder = OkHttpClient.Builder()
            okClient.connectTimeout(60, TimeUnit.SECONDS)
            okClient.readTimeout(60, TimeUnit.SECONDS)
            okClient.addInterceptor(logging)
            val client: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiServices = client.create(ApiServices::class.java)
            return apiServices!!
        }
}