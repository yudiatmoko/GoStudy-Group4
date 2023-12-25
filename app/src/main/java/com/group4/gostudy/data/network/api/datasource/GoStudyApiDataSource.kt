package com.group4.gostudy.data.network.api.datasource

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
import com.group4.gostudy.data.network.api.model.notifcation.NotificationsResponse
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
import com.group4.gostudy.data.network.api.model.verify.VerifyResponse
import com.group4.gostudy.data.network.api.service.GoStudyApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface GoStudyApiDataSource {
    suspend fun getNotifications(): NotificationsResponse

    suspend fun getProfile(): UsersResponse

    suspend fun getCategories(): CategoriesResponse

    suspend fun getCourses(
        category: String?,
        search: String?,
        type: String?,
        level: String?,
        promoPrecentage: Boolean?,
        createAt: Boolean?
    ): CoursesResponseV2

    suspend fun getModules(): ModulesResponse
    suspend fun getChapters(): ChaptersResponse
    suspend fun getCourseId(
        category: String?,
        chapter: String?,
        module: String?
    ): CoursesIdResponse

    suspend fun updateProfile(
        name: RequestBody?,
        phoneNumber: RequestBody?,
        country: RequestBody?,
        city: RequestBody?,
        image: MultipartBody.Part?
    ): UpdateUsersResponse

    suspend fun getCourseById(
        id: Int?
    ): CourseByIdResponse

    suspend fun getChaptersV2(
        id: Int?
    ): CourseByIdResponse

    suspend fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ): UpdatePasswordResponse

    suspend fun getHistoryPayments(): HistoryPaymentsResponse

    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun register(registerRequest: RegisterRequest): RegistersResponse

    suspend fun resendOtp(): OtpResponse

    suspend fun verify(otp: OtpRequest): VerifyResponse

    suspend fun forgotPassword(request: ForgotPasswordRequest): ForgotPasswordResponse
    suspend fun createOrder(paymentRequest: PaymentRequest): PaymentResponse
}

class GoStudyApiDataSourceImpl(
    private val service: GoStudyApiService
) : GoStudyApiDataSource {
    override suspend fun getNotifications(): NotificationsResponse {
        return service.getNotifications()
    }

    override suspend fun getProfile(): UsersResponse {
        return service.getProfile()
    }

    override suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }

    override suspend fun getCourses(
        category: String?,
        search: String?,
        type: String?,
        level: String?,
        promoPrecentage: Boolean?,
        createAt: Boolean?
    ): CoursesResponseV2 {
        return service.getCourses(category, search, type, level, promoPrecentage, createAt)
    }

    override suspend fun getModules(): ModulesResponse {
        return service.getModules()
    }

    override suspend fun getChapters(): ChaptersResponse {
        return service.getChapters()
    }

    override suspend fun getChaptersV2(id: Int?): CourseByIdResponse {
        return service.getChaptersV2(id)
    }

    override suspend fun getCourseId(
        category: String?,
        chapter: String?,
        module: String?
    ): CoursesIdResponse {
        return service.getCourseId(category, module, chapter)
    }

    override suspend fun updateProfile(
        name: RequestBody?,
        phoneNumber: RequestBody?,
        country: RequestBody?,
        city: RequestBody?,
        image: MultipartBody.Part?
    ): UpdateUsersResponse {
        return service.updateProfile(
            name,
            phoneNumber,
            country,
            city,
            image
        )
    }

    override suspend fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest
    ): UpdatePasswordResponse {
        return service.updatePassword(
            updatePasswordRequest
        )
    }

    override suspend fun getHistoryPayments(): HistoryPaymentsResponse {
        return service.getHistoryPayments()
    }

    override suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
        return service.login(loginRequest)
    }

    override suspend fun register(
        registerRequest: RegisterRequest
    ): RegistersResponse {
        return service.register(registerRequest)
    }

    override suspend fun resendOtp(): OtpResponse {
        return service.resendOtp()
    }

    override suspend fun verify(
        otp: OtpRequest
    ): VerifyResponse {
        return service.verify(otp)
    }

    override suspend fun forgotPassword(request: ForgotPasswordRequest): ForgotPasswordResponse {
        return service.forgotPassword(request)
    }

    override suspend fun getCourseById(id: Int?): CourseByIdResponse {
        return service.getCourseById(id)
    }
    override suspend fun createOrder(paymentRequest: PaymentRequest): PaymentResponse {
        return service.createOrder(paymentRequest)
    }
}
