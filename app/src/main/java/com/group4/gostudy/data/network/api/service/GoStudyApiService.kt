package com.group4.gostudy.data.network.api.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group4.gostudy.BuildConfig
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import com.group4.gostudy.data.network.api.model.category.CategoriesResponse
import com.group4.gostudy.data.network.api.model.course.CoursesResponse
import com.group4.gostudy.data.network.api.model.detail.ModuleResponse
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordRequest
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordResponse
import com.group4.gostudy.data.network.api.model.history.HistoriesResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
import com.group4.gostudy.data.network.api.model.notifcation.NotificationsResponse
import com.group4.gostudy.data.network.api.model.otp.OtpRequest
import com.group4.gostudy.data.network.api.model.otp.OtpResponse
import com.group4.gostudy.data.network.api.model.register.RegisterRequest
import com.group4.gostudy.data.network.api.model.register.RegistersResponse
import com.group4.gostudy.data.network.api.model.user.UsersResponse
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordResponse
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUsersResponse
import com.group4.gostudy.data.network.api.model.verify.VerifyResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface GoStudyApiService {
    @GET("user/me")
    suspend fun getProfile(): UsersResponse

    @Multipart
    @PUT("user/update")
    suspend fun updateProfile(
        @Part("name") name: RequestBody?,
        @Part("phoneNumber") phoneNumber: RequestBody?,
        @Part("country") country: RequestBody?,
        @Part("city") city: RequestBody?,
        @Part image: MultipartBody.Part?
    ): UpdateUsersResponse

    @PUT("user/update-password")
    suspend fun updatePassword(
        @Body updatePasswordRequest: UpdatePasswordRequest
    ): UpdatePasswordResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("category")
    suspend fun getCategories(): CategoriesResponse

    @GET("course")
    suspend fun getCourses(
        @Query("categoryName") category: String? = null,
        @Query("search") search: String? = null,
        @Query("type") type: String? = null
    ): CoursesResponse

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegistersResponse

    @POST("auth/resend")
    suspend fun resendOtp(): OtpResponse

    @POST("auth/verify")
    suspend fun verify(
        @Body otp: OtpRequest
    ): VerifyResponse

    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Body forgotPasswordRequest: ForgotPasswordRequest
    ): ForgotPasswordResponse

    @GET("history")
    suspend fun getHistories(): HistoriesResponse

    @GET("notification")
    suspend fun getNotifications(): NotificationsResponse

    @GET("module")
    suspend fun getModules(): ModuleResponse

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
