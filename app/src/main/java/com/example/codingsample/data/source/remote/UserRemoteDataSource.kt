package com.example.codingsample.data.source.remote

import com.example.codingsample.data.UserResult
import com.example.codingsample.data.Result


interface UserRemoteDataSource {
    suspend fun getUser() : Result<UserResult>
}