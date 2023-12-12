package com.group4.gostudy.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _login =
        MutableLiveData<ResultWrapper<String>>()

    val login: LiveData<ResultWrapper<String>>
        get() = _login

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            ).collect {
                _login.postValue(it)
            }
        }
    }
}
