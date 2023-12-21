package com.group4.gostudy.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.network.api.model.login.LoginRequest
import com.group4.gostudy.data.repository.UserRepository
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

class LoginViewModelTest {

    @MockK
    private lateinit var repository: UserRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            LoginViewModel(repository),
            recordPrivateCalls = true
        )
        coEvery { repository.login(any()) } returns flow {
            emit(
                ResultWrapper.Success(
                    "success"
                )
            )
        }
    }

    @Test
    fun `test login result live data`() {
        runTest {
            viewModel.login(LoginRequest("123", "123"))
            coVerify { repository.login(any()) }
            val result = viewModel.login.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
        }
    }
}
