package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.user.toUser
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUserRequest
import com.group4.gostudy.model.User
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface ProfileRepository {
    suspend fun getProfile(userId: String): Flow<ResultWrapper<User>>
    suspend fun updateProfile(
        userId: String,
        updateUserRequest: UpdateUserRequest
    ): Flow<ResultWrapper<User>>

    suspend fun updatePassword(
        userId: String,
        updatePasswordRequest: UpdatePasswordRequest
    ): Boolean

    suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>>
    suspend fun logout(): Boolean
}

class ProfileRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : ProfileRepository {

    override suspend fun getProfile(userId: String): Flow<ResultWrapper<User>> {
        return proceedFlow {
            apiDataSource.getProfile(userId).data.toUser()
        }
    }

    override suspend fun updateProfile(
        userId: String,
        updateUserRequest: UpdateUserRequest
    ): Flow<ResultWrapper<User>> {
        return proceedFlow {
            apiDataSource.updateProfile(
                userId,
                updateUserRequest
            ).data.toUser()
        }
    }

    override suspend fun updatePassword(
        userId: String,
        updatePasswordRequest: UpdatePasswordRequest
    ): Boolean {
        return apiDataSource.updatePassword(
            userId,
            updatePasswordRequest
        ).success ?: false
    }

    override suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>> {
        return proceedFlow {
            apiDataSource.login(loginRequest).data?.accessToken.orEmpty()
        }
    }

    override suspend fun logout(): Boolean {
        return apiDataSource.logout().success ?: false
    }
}
