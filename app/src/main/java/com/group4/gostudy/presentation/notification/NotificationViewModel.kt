package com.group4.gostudy.presentation.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.NotificationRepository
import com.group4.gostudy.model.Notification
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val _notifications = MutableLiveData<ResultWrapper<List<Notification>>>()

    val notifications: LiveData<ResultWrapper<List<Notification>>>
        get() = _notifications

    fun getNotification() {
        viewModelScope.launch(Dispatchers.IO) {
            notificationRepository.getNotifications().collect {
                _notifications.postValue(it)
            }
        }
    }
}
