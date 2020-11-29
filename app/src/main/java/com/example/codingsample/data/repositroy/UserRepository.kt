package com.example.codingsample.data.repositroy

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.codingsample.data.*

import com.example.codingsample.data.source.local.*
import com.example.codingsample.data.source.remote.RestClient
import com.example.codingsample.data.source.remote.UserRemoteDataSource
import com.example.codingsample.data.source.remote.UserRemoteDataSourceImpl

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 */
class UserRepository private constructor(application: Application) {

    private val userRemoteDataSource: UserRemoteDataSource
    private val userLocalDataSource: UserLocalDataSource

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getRepository(app: Application): UserRepository {
            return INSTANCE
                ?: synchronized(this) {
                UserRepository(
                    app
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    init {
        val database = Room.databaseBuilder(
            application.applicationContext,
            UserDatabase::class.java, "Users.db"
        ).build()

        userLocalDataSource = UserLocalDataSourceImpl(database.userDao())
        userRemoteDataSource = UserRemoteDataSourceImpl(RestClient.client)
    }


    fun observeUsers(): LiveData<List<UserDetail>> {
        return userLocalDataSource.observeUsers()
    }

    suspend fun getUsers()  {
        val remoteTasks = userRemoteDataSource.getUser()

        if (remoteTasks is Result.Success) {
            remoteTasks.data.let {
                it?.data.forEach{ user ->
                    insertUser(user)
                }
            }
        } else if (remoteTasks is Result.Error) {
            throw remoteTasks.exception
        }
    }

    private suspend fun insertUser(user: User) {
        val userDetail = UserDataMapper().toUserDetail(user)
        userLocalDataSource.insertUser(userDetail)
    }
}

