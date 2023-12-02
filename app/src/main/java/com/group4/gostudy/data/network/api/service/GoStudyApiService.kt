package com.group4.gostudy.data.network.api.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group4.gostudy.BuildConfig
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import com.group4.gostudy.data.network.api.model.history.HistoriesResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
import com.group4.gostudy.data.network.api.model.notifcation.NotificationsResponse
import com.group4.gostudy.data.network.api.model.user.UsersResponse
import com.group4.gostudy.data.network.api.model.user.logout.LogoutResponse
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordResponse
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUserRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface GoStudyApiService {
    @GET("/api/v1/users/{id}")
    suspend fun getProfile(
        @Path("id") userId: String
    ): UsersResponse

    @PATCH("/api/v1/users/{id}")
    suspend fun updateProfile(
        @Path("id") userId: String,
        @Body updateUserRequest: UpdateUserRequest
    ): UsersResponse

    @PATCH("/api/v1/users/update-password/{id}")
    suspend fun updatePassword(
        @Path("id") userId: String,
        @Body updatePasswordRequest: UpdatePasswordRequest
    ): UpdatePasswordResponse

    @POST("/api/v1/auths/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @DELETE("/api/v1/auths/logout")
    suspend fun logout(): LogoutResponse

    @GET("history")
    suspend fun getHistories(): HistoriesResponse

    @GET("notification")
    suspend fun getNotifications(): NotificationsResponse

    companion object {
        @JvmStatic
        operator fun invoke(chucker: ChuckerInterceptor, userPreferenceDataSource: UserPreferenceDataSource): GoStudyApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chucker)
                .addInterceptor(AuthorizationInterceptor(userPreferenceDataSource))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(okHttpClient)
                .build()
            return retrofit.create(
                GoStudyApiService::class.java
            )
        }
    }
}
