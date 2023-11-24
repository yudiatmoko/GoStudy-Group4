package com.group4.gostudy.data.network.api.datasource

import com.group4.gostudy.data.network.api.model.notifcation.NotificationsResponse
import com.group4.gostudy.data.network.api.model.profile.ProfileResponse
import com.group4.gostudy.data.network.api.service.GoStudyApiService

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface GoStudyApiDataSource {
    suspend fun getNotifications(): NotificationsResponse
    suspend fun getProfile(): ProfileResponse
}

class GoStudyApiDataSourceImpl(
    private val service: GoStudyApiService
) : GoStudyApiDataSource {
    override suspend fun getNotifications(): NotificationsResponse {
        return service.getNotifications()
    }

    override suspend fun getProfile(): ProfileResponse {
        return service.getProfile()
    }
}
