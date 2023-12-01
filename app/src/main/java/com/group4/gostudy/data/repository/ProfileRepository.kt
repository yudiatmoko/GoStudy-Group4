package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.profile.toProfile
import com.group4.gostudy.model.Profile
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface ProfileRepository {
    suspend fun getProfile(): Flow<ResultWrapper<Profile>>
    suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>>
}

class ProfileRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : ProfileRepository {

    override suspend fun getProfile(): Flow<ResultWrapper<Profile>> {
        return proceedFlow {
            apiDataSource.getProfile().data.toProfile()
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>> {
        return proceedFlow {
            apiDataSource.login(loginRequest).data?.token.orEmpty()
        }
    }
}
