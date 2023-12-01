package com.group4.gostudy.data.network.api.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group4.gostudy.BuildConfig
import com.group4.gostudy.data.network.api.model.history.HistoriesResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
import com.group4.gostudy.data.network.api.model.notifcation.NotificationsResponse
import com.group4.gostudy.data.network.api.model.profile.ProfileResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface GoStudyApiService {

    @GET("notification")
    suspend fun getNotifications(): NotificationsResponse

    @GET("auth/me")
    suspend fun getProfile(): ProfileResponse

    @GET("history")
    suspend fun getHistories(): HistoriesResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    companion object {
        @JvmStatic
        operator fun invoke(chucker: ChuckerInterceptor): GoStudyApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chucker)
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
