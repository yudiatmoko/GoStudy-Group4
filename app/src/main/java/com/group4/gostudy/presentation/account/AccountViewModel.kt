package com.group4.gostudy.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class AccountViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.logout()
        }
    }
}
