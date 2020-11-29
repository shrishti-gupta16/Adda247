package com.example.codingsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserDetail")
class UserDetail constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "gender") val gender : String,
    @ColumnInfo(name = "status") val status : String,
    @ColumnInfo(name = "created_at") val createdAt : String,
    @ColumnInfo(name = "updated_at") val updatedAt : String
)