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
    suspend fun getProfile(): Flow<ResultWrapper<User>>
    suspend fun updateProfile(
        updateUserRequest: UpdateUserRequest
    ): Flow<ResultWrapper<User>>

    suspend fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ): String

    suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>>
}

class ProfileRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : ProfileRepository {

    override suspend fun getProfile(): Flow<ResultWrapper<User>> {
        return proceedFlow {
            apiDataSource.getProfile().data.user.toUser()
        }
    }

    override suspend fun updateProfile(
        updateUserRequest: UpdateUserRequest
    ): Flow<ResultWrapper<User>> {
        return proceedFlow {
            apiDataSource.updateProfile(
                updateUserRequest
            ).data.updatedUser.toUser()
        }
    }

    override suspend fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ): String {
        return apiDataSource.updatePassword(
            updatePasswordRequest
        ).message.orEmpty()
    }

    override suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>> {
        return proceedFlow {
            apiDataSource.login(loginRequest).data?.token.orEmpty()
        }
    }
}
