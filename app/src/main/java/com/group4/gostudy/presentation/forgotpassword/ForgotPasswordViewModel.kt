package com.group4.gostudy.presentation.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val repository: UserRepository) : ViewModel() {

    private val _forgotPasswordResult = MutableLiveData<ResultWrapper<String>>()
    val forgotPasswordResult: LiveData<ResultWrapper<String>> get() = _forgotPasswordResult

    fun forgotPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.forgotPassword(email).collect {
                _forgotPasswordResult.postValue(it)
            }
        }
    }
}
