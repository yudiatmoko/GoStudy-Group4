package com.group4.gostudy.presentation.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.network.api.model.register.RegisterRequest
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

class RegisterViewModelTest {

    @MockK
    private lateinit var repository: UserRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            RegisterViewModel(repository),
            recordPrivateCalls = true
        )
        coEvery { repository.register(any()) } returns flow {
            emit(
                ResultWrapper.Success(
                    "Success"
                )
            )
        }
    }

    @Test
    fun `test register result live data`() {
        runTest {
            val mockRegisterRequest = mockk<RegisterRequest>(relaxed = true)
            viewModel.doRegister(mockRegisterRequest)
            coVerify { repository.register(any()) }
            val result = viewModel.registerResult.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
        }
    }
}
