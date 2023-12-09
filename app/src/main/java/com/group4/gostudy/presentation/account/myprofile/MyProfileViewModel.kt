package com.group4.gostudy.presentation.account.myprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.model.User
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class MyProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _profile =
        MutableLiveData<ResultWrapper<User>>()

    val profile: LiveData<ResultWrapper<User>>
        get() = _profile

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getProfile()
                .collect {
                    _profile.postValue(it)
                }
        }
    }

    fun updateProfile(
        name: RequestBody?,
        phoneNumber: RequestBody?,
        country: RequestBody?,
        city: RequestBody?,
        image: MultipartBody.Part?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateProfile(
                name,
                phoneNumber,
                country,
                city,
                image
            ).collect {
                _profile.postValue(it)
            }
        }
    }

    private val _login =
        MutableLiveData<ResultWrapper<String>>()

    val login: LiveData<ResultWrapper<String>>
        get() = _login

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.login(
                LoginRequest(
                    email = "hamatmoko@gmail.com",
                    password = "ilham123"
                )
            ).collect {
                _login.postValue(it)
            }
        }
    }
}
