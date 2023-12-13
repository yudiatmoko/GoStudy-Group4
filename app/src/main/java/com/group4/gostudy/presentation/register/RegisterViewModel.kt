package com.group4.gostudy.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.register.RegisterRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class RegisterViewModel(private val repo: UserRepository) : ViewModel() {

    private val _registerResult = MutableLiveData<ResultWrapper<String>>()

    val registerResult: LiveData<ResultWrapper<String>>
        get() = _registerResult

    fun doRegister(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            repo.register(registerRequest).collect {
                _registerResult.postValue(it)
            }
        }
    }
}
