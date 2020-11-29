package com.example.codingsample.data.source.local

import androidx.lifecycle.LiveData
import com.example.codingsample.data.UserDetail

interface UserLocalDataSource {

    suspend fun insertUser(userDetail: UserDetail)

    fun observeUsers(): LiveData<List<UserDetail>>

}