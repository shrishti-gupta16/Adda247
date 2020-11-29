package com.example.codingsample.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codingsample.data.UserDetail

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDetail: UserDetail)

    @Query("SELECT * FROM UserDetail")
    fun observeUserList(): LiveData<List<UserDetail>>
}