package com.group4.gostudy.data.repository

import app.cash.turbine.test
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.common.BaseResponse
import com.group4.gostudy.data.network.api.model.forgotpassword.DataForgotPasswordResponse
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordRequest
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordResponse
import com.group4.gostudy.data.network.api.model.login.DataLoginResponse
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.network.api.model.login.LoginResponse
import com.group4.gostudy.data.network.api.model.otp.OtpRequest
import com.group4.gostudy.data.network.api.model.otp.OtpResponse
import com.group4.gostudy.data.network.api.model.register.DataRegisterResponse
import com.group4.gostudy.data.network.api.model.register.RegisterRequest
import com.group4.gostudy.data.network.api.model.register.RegistersResponse
import com.group4.gostudy.data.network.api.model.user.DataUser
import com.group4.gostudy.data.network.api.model.user.DataUserResponse
import com.group4.gostudy.data.network.api.model.user.UsersResponse
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordResponse
import com.group4.gostudy.data.network.api.model.user.updateuser.DataUpdateUser
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUsersResponse
import com.group4.gostudy.data.network.api.model.verify.VerifyResponse
import com.group4.gostudy.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    @MockK
    lateinit var datasource: GoStudyApiDataSource

    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = UserRepositoryImpl(datasource)
    }

    @Test
    fun `getProfile result success`() {
        val mockResponse = UsersResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataUser(
                DataUserResponse(
                    city = null,
                    country = null,
                    createdAt = null,
                    email = null,
                    id = null,
                    imageId = null,
                    imageUrl = null,
                    name = "TestingName",
                    password = "TestingPass",
                    phoneNumber = null,
                    role = null,
                    updatedAt = null,
                    verify = null
                )
            )
        )
        coEvery { datasource.getProfile() } returns mockResponse
        runTest {
            repository.getProfile().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.name, "TestingName")
                TestCase.assertEquals(data.payload?.password, "TestingPass")
                coVerify { datasource.getProfile() }
            }
        }
    }

    @Test
    fun `getProfile result loading`() {
        val mockResponse = UsersResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataUser(
                DataUserResponse(
                    city = null,
                    country = null,
                    createdAt = null,
                    email = null,
                    id = null,
                    imageId = null,
                    imageUrl = null,
                    name = "TestingName",
                    password = "TestingPass",
                    phoneNumber = null,
                    role = null,
                    updatedAt = null,
                    verify = null
                )
            )
        )
        coEvery { datasource.getProfile() } returns mockResponse
        runTest {
            repository.getProfile().map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getProfile() }
            }
        }
    }

    @Test
    fun `getProfile result error`() {
        coEvery { datasource.getProfile() } throws IllegalStateException("mock error")
        runTest {
            repository.getProfile().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getProfile() }
            }
        }
    }

    @Test
    fun `updateProfile result success`() {
        val mockResponse = UpdateUsersResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataUpdateUser(
                DataUserResponse(
                    city = null,
                    country = null,
                    createdAt = null,
                    email = null,
                    id = null,
                    imageId = null,
                    imageUrl = null,
                    name = "UpdateName",
                    password = "UpdatePass",
                    phoneNumber = null,
                    role = null,
                    updatedAt = null,
                    verify = null
                )
            )
        )
        coEvery { datasource.updateProfile(any(), any(), any(), any(), any()) } returns mockResponse
        runTest {
            repository.updateProfile(null, null, null, null, null).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.name, "UpdateName")
                TestCase.assertEquals(data.payload?.password, "UpdatePass")
                coVerify { datasource.updateProfile(null, null, null, null, null) }
            }
        }
    }

    @Test
    fun `updateProfile result error`() {
        coEvery { datasource.updateProfile(any(), any(), any(), any(), any()) } throws IllegalStateException("mock error")
        runTest {
            repository.updateProfile(null, null, null, null, null).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.updateProfile(null, null, null, null, null) }
            }
        }
    }

    @Test
    fun `updateProfile result loading`() {
        val mockResponse = UpdateUsersResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataUpdateUser(
                DataUserResponse(
                    city = null,
                    country = null,
                    createdAt = null,
                    email = null,
                    id = null,
                    imageId = null,
                    imageUrl = null,
                    name = "UpdateName",
                    password = "UpdatePass",
                    phoneNumber = null,
                    role = null,
                    updatedAt = null,
                    verify = null
                )
            )
        )
        coEvery { datasource.updateProfile(any(), any(), any(), any(), any()) } returns mockResponse
        runTest {
            repository.updateProfile(null, null, null, null, null).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.updateProfile(null, null, null, null, null) }
            }
        }
    }

    @Test
    fun `updatePassword result success`() {
        val mockResponse = UpdatePasswordResponse(
            message = "Update Password Success",
            status = "Success"
        )
        val mockUpdatePasswordRequest = mockk<UpdatePasswordRequest>(relaxed = true)
        coEvery { datasource.updatePassword(any()) } returns mockResponse
        runTest {
            repository.updatePassword(mockUpdatePasswordRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload, "Update Password Success")
                coVerify { datasource.updatePassword(mockUpdatePasswordRequest) }
            }
        }
    }

    @Test
    fun `updatePassword result loading`() {
        val mockResponse = UpdatePasswordResponse(
            message = "Update Password Success",
            status = "Success"
        )
        val mockUpdatePasswordRequest = mockk<UpdatePasswordRequest>(relaxed = true)
        coEvery { datasource.updatePassword(any()) } returns mockResponse
        runTest {
            repository.updatePassword(mockUpdatePasswordRequest).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.updatePassword(mockUpdatePasswordRequest) }
            }
        }
    }

    @Test
    fun `updatePassword result error`() {
        val mockUpdatePasswordRequest = mockk<UpdatePasswordRequest>(relaxed = true)
        coEvery { datasource.updatePassword(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.updatePassword(mockUpdatePasswordRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.updatePassword(mockUpdatePasswordRequest) }
            }
        }
    }

    @Test
    fun `register result success`() {
        val mockResponse = RegistersResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataRegisterResponse(
                token = "token",
                user = DataUserResponse(
                    city = null,
                    country = null,
                    createdAt = null,
                    email = null,
                    id = null,
                    imageId = null,
                    imageUrl = null,
                    name = "RegisterName",
                    password = "RegisterPass",
                    phoneNumber = null,
                    role = null,
                    updatedAt = null,
                    verify = null
                )
            )
        )
        val mockRegisterRequest = mockk<RegisterRequest>(relaxed = true)
        coEvery { datasource.register(any()) } returns mockResponse
        runTest {
            repository.register(mockRegisterRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload, "token")
                coVerify { datasource.register(mockRegisterRequest) }
            }
        }
    }

    @Test
    fun `register result loading`() {
        val mockResponse = RegistersResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataRegisterResponse(
                token = "token",
                user = DataUserResponse(
                    city = null,
                    country = null,
                    createdAt = null,
                    email = null,
                    id = null,
                    imageId = null,
                    imageUrl = null,
                    name = "RegisterName",
                    password = "RegisterPass",
                    phoneNumber = null,
                    role = null,
                    updatedAt = null,
                    verify = null
                )
            )
        )
        val mockRegisterRequest = mockk<RegisterRequest>(relaxed = true)
        coEvery { datasource.register(any()) } returns mockResponse
        runTest {
            repository.register(mockRegisterRequest).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.register(mockRegisterRequest) }
            }
        }
    }

    @Test
    fun `register result error`() {
        val mockRegisterRequest = mockk<RegisterRequest>(relaxed = true)
        coEvery { datasource.register(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.register(mockRegisterRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.register(mockRegisterRequest) }
            }
        }
    }

    @Test
    fun `login result success`() {
        val mockResponse = LoginResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataLoginResponse(
                token = "token"
            )
        )
        val mockLoginRequest = mockk<LoginRequest>(relaxed = true)
        coEvery { datasource.login(any()) } returns mockResponse
        runTest {
            repository.login(mockLoginRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload, "token")
                coVerify { datasource.login(mockLoginRequest) }
            }
        }
    }

    @Test
    fun `login result loading`() {
        val mockResponse = LoginResponse(
            baseResponse = BaseResponse(
                message = null,
                status = null
            ),
            data = DataLoginResponse(
                token = "token"
            )
        )
        val mockLoginRequest = mockk<LoginRequest>(relaxed = true)
        coEvery { datasource.login(any()) } returns mockResponse
        runTest {
            repository.login(mockLoginRequest).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.login(mockLoginRequest) }
            }
        }
    }

    @Test
    fun `login result error`() {
        val mockLoginRequest = mockk<LoginRequest>(relaxed = true)
        coEvery { datasource.login(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.login(mockLoginRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.login(mockLoginRequest) }
            }
        }
    }

    @Test
    fun `verify result success`() {
        val mockResponse = VerifyResponse(
            message = "Verify Success",
            status = null
        )
        val mockVerifyRequest = mockk<OtpRequest>(relaxed = true)
        coEvery { datasource.verify(any()) } returns mockResponse
        runTest {
            repository.verify(mockVerifyRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload, "Verify Success")
                coVerify { datasource.verify(mockVerifyRequest) }
            }
        }
    }

    @Test
    fun `verify result loading`() {
        val mockResponse = VerifyResponse(
            message = null,
            status = null
        )
        val mockVerifyRequest = mockk<OtpRequest>(relaxed = true)
        coEvery { datasource.verify(any()) } returns mockResponse
        runTest {
            repository.verify(mockVerifyRequest).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.verify(mockVerifyRequest) }
            }
        }
    }

    @Test
    fun `verify result error`() {
        val mockVerifyRequest = mockk<OtpRequest>(relaxed = true)
        coEvery { datasource.verify(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.verify(mockVerifyRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.verify(mockVerifyRequest) }
            }
        }
    }

    @Test
    fun `resendOTP result success`() {
        val mockResponse = OtpResponse(
            response = BaseResponse(
                message = "Resend Success",
                status = "Success"
            )
        )
        coEvery { datasource.resendOtp() } returns mockResponse
        runTest {
            repository.resendOtp().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.response?.message, "Resend Success")
                coVerify { datasource.resendOtp() }
            }
        }
    }

    @Test
    fun `resendOTP result loading`() {
        val mockResponse = OtpResponse(
            response = BaseResponse(
                message = "Resend Success",
                status = "Success"
            )
        )
        coEvery { datasource.resendOtp() } returns mockResponse
        runTest {
            repository.resendOtp().map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.resendOtp() }
            }
        }
    }

    @Test
    fun `resendOTP result error`() {
        coEvery { datasource.resendOtp() } throws IllegalStateException("mock error")
        runTest {
            repository.resendOtp().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.resendOtp() }
            }
        }
    }

    @Test
    fun `forgotPassword result success`() {
        val mockResponse = ForgotPasswordResponse(
            message = "Request Reset Password Success",
            status = "Success",
            data = DataForgotPasswordResponse(
                token = "token"
            )
        )
        val mockRequest = mockk<ForgotPasswordRequest>(relaxed = true)
        coEvery { datasource.forgotPassword(any()) } returns mockResponse
        runTest {
            repository.forgotPassword(mockRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload, "Request Reset Password Success")
                coVerify { datasource.forgotPassword(mockRequest) }
            }
        }
    }

    @Test
    fun `forgotPassword result loading`() {
        val mockResponse = ForgotPasswordResponse(
            message = "Request Reset Password Success",
            status = "Success",
            data = DataForgotPasswordResponse(
                token = "token"
            )
        )
        val mockRequest = mockk<ForgotPasswordRequest>(relaxed = true)
        coEvery { datasource.forgotPassword(any()) } returns mockResponse
        runTest {
            repository.forgotPassword(mockRequest).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.forgotPassword(mockRequest) }
            }
        }
    }

    @Test
    fun `forgotPassword result error`() {
        val mockRequest = mockk<ForgotPasswordRequest>(relaxed = true)
        coEvery { datasource.forgotPassword(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.forgotPassword(mockRequest).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.forgotPassword(any()) }
            }
        }
    }
}
