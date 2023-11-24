package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.notifcation.toNotificationList
import com.group4.gostudy.data.network.api.model.profile.toProfile
import com.group4.gostudy.model.Notification
import com.group4.gostudy.model.Profile
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface NotificationRepository {
    suspend fun getNotifications(): Flow<ResultWrapper<List<Notification>>>
    suspend fun getProfile(): Flow<ResultWrapper<Profile>>
}

class NotificationRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : NotificationRepository {
    override suspend fun getNotifications(): Flow<ResultWrapper<List<Notification>>> {
        return proceedFlow {
            apiDataSource.getNotifications().data?.toNotificationList() ?: emptyList()
        }
    }

    override suspend fun getProfile(): Flow<ResultWrapper<Profile>> {
        return proceedFlow {
            apiDataSource.getProfile().data.toProfile()
        }
    }
}
