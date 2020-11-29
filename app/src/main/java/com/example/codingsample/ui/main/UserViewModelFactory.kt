package com.example.codingsample.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingsample.data.repositroy.UserRepository

class UserViewModelFactory constructor(val application: Application,
                                       val userRepository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java!!)) {
            UserViewModel(application, userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}