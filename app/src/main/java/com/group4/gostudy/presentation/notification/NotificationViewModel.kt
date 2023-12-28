package com.group4.gostudy.presentation.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.AllNotif
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class NotificationViewModel(
    private val repository: CourseRepository
) : ViewModel() {

    private val _notifications = MutableLiveData<ResultWrapper<List<AllNotif>>>()

    val notifications: LiveData<ResultWrapper<List<AllNotif>>>
        get() = _notifications

    fun getNotification() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNotifications().collect {
                _notifications.postValue(it)
            }
        }
    }
}
