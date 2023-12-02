package com.group4.gostudy.data.network.api.datasource

import com.group4.gostudy.data.network.api.model.history.HistoriesResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
import com.group4.gostudy.data.network.api.model.notifcation.NotificationsResponse
import com.group4.gostudy.data.network.api.model.user.UsersResponse
import com.group4.gostudy.data.network.api.model.user.logout.LogoutResponse
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordResponse
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUserRequest
import com.group4.gostudy.data.network.api.service.GoStudyApiService

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface GoStudyApiDataSource {
    suspend fun getNotifications(): NotificationsResponse

    suspend fun getProfile(userId: String): UsersResponse

    suspend fun updateProfile(
        userId: String,
        updateUserRequest: UpdateUserRequest
    ): UsersResponse

    suspend fun updatePassword(
        userId: String,
        updatePasswordRequest: UpdatePasswordRequest
    ): UpdatePasswordResponse

    suspend fun getHistories(): HistoriesResponse

    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun logout(): LogoutResponse
}

class GoStudyApiDataSourceImpl(
    private val service: GoStudyApiService
) : GoStudyApiDataSource {
    override suspend fun getNotifications(): NotificationsResponse {
        return service.getNotifications()
    }

    override suspend fun getProfile(userId: String): UsersResponse {
        return service.getProfile(userId)
    }

    override suspend fun updateProfile(
        userId: String,
        updateUserRequest: UpdateUserRequest
    ): UsersResponse {
        return service.updateProfile(
            userId,
            updateUserRequest
        )
    }

    override suspend fun updatePassword(
        userId: String,
        updatePasswordRequest: UpdatePasswordRequest
    ): UpdatePasswordResponse {
        return service.updatePassword(
            userId,
            updatePasswordRequest
        )
    }

    override suspend fun getHistories(): HistoriesResponse {
        return service.getHistories()
    }

    override suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
        return service.login(loginRequest)
    }

    override suspend fun logout(): LogoutResponse {
        return service.logout()
    }
}
