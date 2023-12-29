package com.group4.gostudy.data.network.api.datasource

import com.group4.gostudy.data.network.api.model.category.CategoriesResponse
import com.group4.gostudy.data.network.api.model.coursebyid.CourseByIdResponse
import com.group4.gostudy.data.network.api.model.coursev2.CoursesResponseV2
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordRequest
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordResponse
import com.group4.gostudy.data.network.api.model.historypayment.HistoryPaymentsResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
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
import com.group4.gostudy.data.network.api.service.GoStudyApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoStudyApiDataSourceImplTest {

    @MockK
    lateinit var service: GoStudyApiService

    private lateinit var dataSource: GoStudyApiDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = GoStudyApiDataSourceImpl(service)
    }

    @Test
    fun getNotifications() {
        runTest {
            val mockResponse = mockk<NotificationsResponse>(relaxed = true)
            coEvery { service.getNotifications() } returns mockResponse
            val response = dataSource.getNotifications()
            coVerify { service.getNotifications() }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getProfile() {
        runTest {
            val mockResponse = mockk<UsersResponse>(relaxed = true)
            coEvery { service.getProfile() } returns mockResponse
            val response = dataSource.getProfile()
            coVerify { service.getProfile() }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getCategories() {
        runTest {
            val mockResponse = mockk<CategoriesResponse>(relaxed = true)
            coEvery { service.getCategories() } returns mockResponse
            val response = dataSource.getCategories()
            coVerify { service.getCategories() }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getCourses() {
        runTest {
            val mockResponse = mockk<CoursesResponseV2>(relaxed = true)
            coEvery {
                service.getCourses(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns mockResponse
            val response = dataSource.getCourses(
                "",
                "",
                "",
                "",
                null,
                null,
                null
            )
            coVerify {
                service.getCourses(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getUserCourses() {
        runTest {
            val mockResponse = mockk<UserCourseResponseV2>(relaxed = true)
            coEvery {
                service.getUserCourse(
                    any(),
                    any()
                )
            } returns mockResponse
            val response = dataSource.getUserCourse(
                "",
                ""
            )
            coVerify {
                service.getUserCourse(
                    any(),
                    any()
                )
            }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getCourseId() {
        runTest {
            val mockResponse = mockk<CourseByIdResponse>(relaxed = true)
            coEvery { service.getCourseById(any()) } returns mockResponse
            val response = dataSource.getCourseById(0)
            coVerify { service.getCourseById(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getUserCourseById() {
        runTest {
            val mockResponse = mockk<UserCourseById>(relaxed = true)
            coEvery { service.getUserCourseById(any()) } returns mockResponse
            val response = dataSource.getUserCourseById(0)
            coVerify { service.getUserCourseById(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun createOrder() {
        runTest {
            val mockResponse = mockk<PaymentResponse>(relaxed = true)
            coEvery { service.createOrder(any()) } returns mockResponse
            val mockBody = mockk<PaymentRequest>(relaxed = true)
            val response = dataSource.createOrder(mockBody)
            coVerify { service.createOrder(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getChapters() {
        runTest {
            val mockResponse = mockk<CourseByIdResponse>(relaxed = true)
            coEvery { service.getChaptersV2(any()) } returns mockResponse
            val response = dataSource.getChaptersV2(0)
            coVerify { service.getChaptersV2(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun updateProfile() {
        runTest {
            val mockResponse = mockk<UpdateUsersResponse>(relaxed = true)
            coEvery { service.updateProfile(any(), any(), any(), any(), any()) } returns mockResponse
            val response = dataSource.updateProfile(null, null, null, null, null)
            coVerify { service.updateProfile(any(), any(), any(), any(), any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun updatePassword() {
        runTest {
            val mockResponse = mockk<UpdatePasswordResponse>(relaxed = true)
            coEvery { service.updatePassword(any()) } returns mockResponse
            val mockBodyUpdatePassword = mockk<UpdatePasswordRequest>(relaxed = true)
            val response = dataSource.updatePassword(mockBodyUpdatePassword)
            coVerify { service.updatePassword(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun getHistories() {
        runTest {
            val mockResponse = mockk<HistoryPaymentsResponse>(relaxed = true)
            coEvery { service.getHistoryPayments() } returns mockResponse
            val response = dataSource.getHistoryPayments()
            coVerify { service.getHistoryPayments() }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun login() {
        runTest {
            val mockResponse = mockk<LoginResponse>(relaxed = true)
            coEvery { service.login(any()) } returns mockResponse
            val mockRequestLogin = mockk<LoginRequest>(relaxed = true)
            val response = dataSource.login(mockRequestLogin)
            coVerify { service.login(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun register() {
        runTest {
            val mockResponse = mockk<RegistersResponse>(relaxed = true)
            coEvery { service.register(any()) } returns mockResponse
            val mockRequestRegister = mockk<RegisterRequest>(relaxed = true)
            val response = dataSource.register(mockRequestRegister)
            coVerify { service.register(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun resendOtp() {
        runTest {
            val mockResponse = mockk<OtpResponse>(relaxed = true)
            coEvery { service.resendOtp() } returns mockResponse
            val response = dataSource.resendOtp()
            coVerify { service.resendOtp() }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun verify() {
        runTest {
            val mockResponse = mockk<VerifyResponse>(relaxed = true)
            coEvery { service.verify(any()) } returns mockResponse
            val mockRequestOTP = mockk<OtpRequest>(relaxed = true)
            val response = dataSource.verify(mockRequestOTP)
            coVerify { service.verify(any()) }
            assertEquals(response, mockResponse)
        }
    }

    @Test
    fun forgotPassword() {
        runTest {
            val mockResponse = mockk<ForgotPasswordResponse>(relaxed = true)
            coEvery { service.forgotPassword(any()) } returns mockResponse
            val mockRequestForgotPassword = mockk<ForgotPasswordRequest>(relaxed = true)
            val response = dataSource.forgotPassword(mockRequestForgotPassword)
            coVerify { service.forgotPassword(any()) }
            assertEquals(response, mockResponse)
        }
    }
}
