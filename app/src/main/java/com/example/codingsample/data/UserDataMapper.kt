package com.example.codingsample.data

import com.google.gson.Gson

class UserDataMapper {

    fun toUserDetail(user: User) : UserDetail {
        return UserDetail(
            id = user.id,
            name = user.name,
            email = user.email,
            gender = user.gender,
            status = user.status,
            createdAt =  user.createdAt,
            updatedAt = user.updatedAt
        )
    }

    fun toUser(user: UserDetail) : User {
        return User(
            id = user.id,
            name = user.name,
            email = user.email,
            gender = user.gender,
            status = user.status,
            createdAt =  user.createdAt,
            updatedAt = user.updatedAt
        )
    }

}