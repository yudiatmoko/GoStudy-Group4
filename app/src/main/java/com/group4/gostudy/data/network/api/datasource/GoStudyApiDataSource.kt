package com.group4.gostudy.data.network.api.datasource

import com.group4.gostudy.data.network.api.model.history.HistoriesResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
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
    suspend fun getHistories(): HistoriesResponse
    suspend fun login(loginRequest: LoginRequest): LoginResponse
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

    override suspend fun getHistories(): HistoriesResponse {
        return service.getHistories()
    }

    override suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
        return service.login(loginRequest)
    }
}
