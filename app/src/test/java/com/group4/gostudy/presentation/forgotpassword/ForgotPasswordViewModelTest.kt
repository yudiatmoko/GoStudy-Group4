package com.group4.gostudy.presentation.forgotpassword

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.network.api.model.forgotpassword.ForgotPasswordRequest
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.tools.MainCoroutineRule
import com.group4.gostudy.tools.getOrAwaitValue
import com.group4.gostudy.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
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

class ForgotPasswordViewModelTest {

    @MockK
    private lateinit var repository: UserRepository

    @MockK
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: ForgotPasswordViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            ForgotPasswordViewModel(repository, mainViewModel),
            recordPrivateCalls = true
        )
        coEvery { repository.forgotPassword(any()) } returns flow {
            emit(
                ResultWrapper.Success(
                    "success"
                )
            )
        }
    }

    @Test
    fun `test forgot password live data`() {
        runTest {
            viewModel.forgotPassword(
                ForgotPasswordRequest("email")
            )
            coVerify { repository.forgotPassword(any()) }
            val result = viewModel.forgotPasswordResult.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
        }
    }
}
