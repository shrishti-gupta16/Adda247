package com.example.codingsample.data.source.local

import androidx.lifecycle.LiveData
import com.example.codingsample.data.UserDetail

class UserLocalDataSourceImpl (private val userDao: UserDao) : UserLocalDataSource {

    override suspend fun insertUser(userDetail: UserDetail) {
        userDao.insertUser(userDetail)
    }


    override fun observeUsers(): LiveData<List<UserDetail>> {
        return userDao.observeUserList();
    }
}