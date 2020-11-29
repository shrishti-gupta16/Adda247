// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codingsample.data.UserDetail

/**
 * The Room Database that contains the Task table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [UserDetail::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
