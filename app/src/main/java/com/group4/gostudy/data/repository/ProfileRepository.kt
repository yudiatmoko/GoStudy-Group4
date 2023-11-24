package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
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
}

class ProfileRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : ProfileRepository {

    override suspend fun getProfile(): Flow<ResultWrapper<Profile>> {
        return proceedFlow {
            apiDataSource.getProfile().data.toProfile()
        }
    }
}
