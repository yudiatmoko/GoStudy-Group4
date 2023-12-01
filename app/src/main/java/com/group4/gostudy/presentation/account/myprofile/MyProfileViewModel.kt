package com.group4.gostudy.presentation.account.myprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.repository.ProfileRepository
import com.group4.gostudy.model.Profile
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

    private val _profile = MutableLiveData<ResultWrapper<Profile>>()

    val profile: LiveData<ResultWrapper<Profile>>
        get() = _profile

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getProfile().collect {
                _profile.postValue(it)
            }
        }
    }

    private val _login = MutableLiveData<ResultWrapper<String>>()

    val login: LiveData<ResultWrapper<String>>
        get() = _login

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.login(
                LoginRequest(
                    email = "ilham@mail.com",
                    password = "jawarera483"
                )
            ).collect {
                _login.postValue(it)
            }
        }
    }
}
