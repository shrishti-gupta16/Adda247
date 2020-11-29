package com.example.codingsample.data.source.remote

import com.example.codingsample.data.UserResult
import com.example.codingsample.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImpl(private val service: ApiServices) : UserRemoteDataSource {

    override suspend fun getUser(): Result<UserResult> = withContext(Dispatchers.IO) {
        return@withContext try {
            val call = service?.getUser()
            val list = call?.execute()!!.body()
            Result.Success(list)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}