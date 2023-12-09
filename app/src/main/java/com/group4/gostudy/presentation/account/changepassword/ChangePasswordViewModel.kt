package com.group4.gostudy.presentation.account.changepassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class ChangePasswordViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _updatedPassword =
        MutableLiveData<ResultWrapper<String>>()

    val updatedPassword: LiveData<ResultWrapper<String>>
        get() = _updatedPassword

    fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updatePassword(
                updatePasswordRequest
            ).collect {
                _updatedPassword.postValue(it)
            }
        }
    }
}
