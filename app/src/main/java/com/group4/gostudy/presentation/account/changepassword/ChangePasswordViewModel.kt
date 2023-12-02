package com.group4.gostudy.presentation.account.changepassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class ChangePasswordViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun updatePassword(
        userId: String,
        updatePasswordRequest: UpdatePasswordRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.updatePassword(
                userId,
                updatePasswordRequest
            )
        }
    }
}
