package com.group4.gostudy.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.model.User
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class MainViewModel(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val userRepository: UserRepository
) : ViewModel() {

    val userTokenLiveData = userPreferenceDataSource.getUserTokenFlow().asLiveData(Dispatchers.IO)

    fun setUserToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferenceDataSource.setUserToken(token)
        }
    }

    fun removeUserToken() {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferenceDataSource.removeUserToken()
        }
    }

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

    fun getFirstName(profile: User): String? {
        val fullName = profile.name
        val split = fullName?.split(" ")
        return split?.get(0)
    }
}
