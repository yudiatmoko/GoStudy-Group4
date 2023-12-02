package com.group4.gostudy.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class MainViewModel(
    private val userPreferenceDataSource: UserPreferenceDataSource
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
}
