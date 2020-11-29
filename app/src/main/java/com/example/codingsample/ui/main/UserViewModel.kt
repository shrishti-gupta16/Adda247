// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.codingsample.data.User
import com.example.codingsample.data.UserDataMapper
import com.example.codingsample.data.UserDetail
import com.example.codingsample.data.repositroy.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application, userRepository: UserRepository) : AndroidViewModel(application) {

    val dataLoading = MutableLiveData<Boolean>()
    val isNetworkAvailable = MutableLiveData<Boolean>()

    private val refreshItems = MutableLiveData<Boolean>()
    private val _items: LiveData<List<User>> = refreshItems.switchMap {
        dataLoading.value = true
        if (isNetworkAvailable.value!!) {
            viewModelScope.launch {
                if (isNetworkAvailable.value!!) {
                    userRepository.getUsers()
                }
                dataLoading.value = false
            }
        }
        userRepository.observeUsers().switchMap { filterUsers(it) }
    }

    val items : LiveData<List<User>> = _items

    fun loadUsers() {
        refreshItems.value = true
    }

    private fun filterUsers(userList: List<UserDetail>): LiveData<List<User>> {
        val result = MutableLiveData<List<User>>()

        viewModelScope.launch {
            val mapper = UserDataMapper()
            val finalList = ArrayList<User>()
            userList.forEach {
                finalList.add(mapper.toUser(it))
            }
            result.value = finalList
        }

        return result
    }
}