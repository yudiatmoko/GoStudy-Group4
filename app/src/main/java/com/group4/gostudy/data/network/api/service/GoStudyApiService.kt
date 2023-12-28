package com.group4.gostudy.data.network.api.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group4.gostudy.BuildConfig
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import com.group4.gostudy.data.network.api.model.category.CategoriesResponse
import com.group4.gostudy.data.network.api.model.chapter.ChaptersResponse
import com.group4.gostudy.data.network.api.model.coursebyid.CourseByIdResponse
import com.group4.gostudy.data.network.api.model.coursev2.CoursesResponseV2
import com.group4.gostudy.data.network.api.model.detail.CoursesIdResponse
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordRequest
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordResponse
import com.group4.gostudy.data.network.api.model.historypayment.HistoryPaymentsResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
import com.group4.gostudy.data.network.api.model.module.ModulesResponse
import com.group4.gostudy.data.network.api.model.notification.NotificationsResponse
import com.group4.gostudy.data.network.api.model.otp.OtpRequest
import com.group4.gostudy.data.network.api.model.otp.OtpResponse
import com.group4.gostudy.data.network.api.model.payment.PaymentRequest
import com.group4.gostudy.data.network.api.model.payment.PaymentResponse
import com.group4.gostudy.data.network.api.model.register.RegisterRequest
import com.group4.gostudy.data.network.api.model.register.RegistersResponse
import com.group4.gostudy.data.network.api.model.user.UsersResponse
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordResponse
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUsersResponse
import com.group4.gostudy.data.network.api.model.usercourseid.UserCourseById
import com.group4.gostudy.data.network.api.model.usercoursev2.UserCourseResponseV2
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
import retrofit2.http.Path
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
        @Query("type") type: String? = null,
        @Query("level") level: String? = null,
        @Query("createAt") createAt: Boolean? = null,
        @Query("promoPrecentage") promoPrecentage: Boolean? = null,
        @Query("rating") rating: Boolean? = null
    ): CoursesResponseV2

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

    @GET("payment/history")
    suspend fun getHistoryPayments(): HistoryPaymentsResponse

    @GET("my-notification")
    suspend fun getNotifications(): NotificationsResponse

    @GET("module")
    suspend fun getModules(): ModulesResponse

    @GET("chapter")
    suspend fun getChapters(): ChaptersResponse

    @GET("course/{id}")
    suspend fun getCourseId(
        @Query("categoryName") category: String? = null,
        @Query("moduleName") module: String? = null,
        @Query("chapterName") chapter: String? = null
    ): CoursesIdResponse

    @GET("course/{id}")
    suspend fun getCourseById(@Path("id") id: Int? = null): CourseByIdResponse

    @GET("course/{id}")
    suspend fun getChaptersV2(@Path("id") id: Int? = null): CourseByIdResponse

    @POST("payment")
    suspend fun createOrder(@Body paymentRequest: PaymentRequest): PaymentResponse

    @GET("view-course")
    suspend fun getUserCourse(
        @Query("status")status: String? = null,
        @Query("search")search: String? = null
    ): UserCourseResponseV2

    @GET("view-course/course/{courseId}")
    suspend fun getUserCourseById(@Path("courseId") id: Int? = null): UserCourseById

    companion object {
        @JvmStatic
        operator fun invoke(
            chucker: ChuckerInterceptor,
            userPreferenceDataSource: UserPreferenceDataSource
        ): GoStudyApiService {
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
