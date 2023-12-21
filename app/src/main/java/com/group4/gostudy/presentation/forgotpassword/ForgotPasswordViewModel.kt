package com.group4.gostudy.presentation.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val repository: UserRepository, private val mainViewModel: MainViewModel) : ViewModel() {

    private val _forgotPasswordResult = MutableLiveData<ResultWrapper<String>>()
    val forgotPasswordResult: LiveData<ResultWrapper<String>> get() = _forgotPasswordResult

    private val _tokenResult = MutableLiveData<String>()
    val tokenResult: LiveData<String> get() = _tokenResult

    fun forgotPassword(email: ForgotPasswordRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.forgotPassword(email).collect {
                when (it) {
                    is ResultWrapper.Success -> {
                        val token = it.payload
                        token?.let { nonNullToken ->
                            mainViewModel.setUserToken(nonNullToken)
                        }
                        _forgotPasswordResult.postValue(it)
                    }
                    is ResultWrapper.Error -> {
                        _forgotPasswordResult.postValue(it)
                    }
                    is ResultWrapper.Loading -> {
                        _forgotPasswordResult.postValue(it)
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
