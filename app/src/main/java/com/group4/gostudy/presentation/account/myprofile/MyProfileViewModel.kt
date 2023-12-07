package com.group4.gostudy.presentation.account.myprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUserRequest
import com.group4.gostudy.data.repository.ProfileRepository
import com.group4.gostudy.model.User
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class MyProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profile =
        MutableLiveData<ResultWrapper<User>>()

    val profile: LiveData<ResultWrapper<User>>
        get() = _profile

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getProfile()
                .collect {
                    _profile.postValue(it)
                }
        }
    }

    fun updateProfile(
        updateUserRequest: UpdateUserRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.updateProfile(
                updateUserRequest
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
            profileRepository.login(
                LoginRequest(
                    email = "johndoe@mail.com",
                    password = "jawa123123"
                )
            ).collect {
                _login.postValue(it)
            }
        }
    }
}
