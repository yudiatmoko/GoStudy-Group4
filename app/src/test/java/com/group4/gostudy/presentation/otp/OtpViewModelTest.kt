package com.group4.gostudy.presentation.otp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.network.api.model.otp.OtpRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.tools.MainCoroutineRule
import com.group4.gostudy.tools.getOrAwaitValue
import com.group4.gostudy.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class OtpViewModelTest {

    @MockK
    private lateinit var repository: UserRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: OtpViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            OtpViewModel(repository),
            recordPrivateCalls = true
        )
        coEvery { repository.verify(any()) } returns flow {
            emit(
                ResultWrapper.Success(
                    "Success"
                )
            )
        }
        coEvery { repository.resendOtp() } returns flow {
            emit(
                ResultWrapper.Success(
                    mockk(relaxed = true)
                )
            )
        }
    }

    @Test
    fun `test verify live data`() {
        runTest {
            viewModel.verifyResult(OtpRequest("1234"))
            coVerify { repository.verify(any()) }
            val result = viewModel.verify.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
        }
    }

    @Test
    fun `test resendOtp live data`() {
        runTest {
            viewModel.resendOtp()
            coVerify { repository.resendOtp() }
            val result = viewModel.resendOtp.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
        }
    }
}
