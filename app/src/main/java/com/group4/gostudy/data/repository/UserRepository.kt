package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.user.toUser
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.model.User
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import okhttp3.MultipartBody
import okhttp3.RequestBody

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface UserRepository {
    suspend fun getProfile(): Flow<ResultWrapper<User>>
    suspend fun updateProfile(
        name: RequestBody?,
        phoneNumber: RequestBody?,
        country: RequestBody?,
        city: RequestBody?,
        image: MultipartBody.Part?
    ): Flow<ResultWrapper<User>>

    suspend fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ): Flow<ResultWrapper<String>>

    suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>>
}

class UserRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : UserRepository {

    override suspend fun getProfile(): Flow<ResultWrapper<User>> {
        return proceedFlow {
            apiDataSource.getProfile().data.user.toUser()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun updateProfile(
        name: RequestBody?,
        phoneNumber: RequestBody?,
        country: RequestBody?,
        city: RequestBody?,
        image: MultipartBody.Part?
    ): Flow<ResultWrapper<User>> {
        return proceedFlow {
            apiDataSource.updateProfile(
                name,
                phoneNumber,
                country,
                city,
                image
            ).data.updatedUser.toUser()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ): Flow<ResultWrapper<String>> {
        return proceedFlow {
            apiDataSource.updatePassword(updatePasswordRequest).message.orEmpty()
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Flow<ResultWrapper<String>> {
        return proceedFlow {
            apiDataSource.login(loginRequest).data?.token.orEmpty()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }
}