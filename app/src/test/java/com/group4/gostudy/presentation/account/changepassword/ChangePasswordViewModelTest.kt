package com.group4.gostudy.presentation.account.changepassword

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
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

class ChangePasswordViewModelTest {

    @MockK
    private lateinit var repository: UserRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: ChangePasswordViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            ChangePasswordViewModel(repository),
            recordPrivateCalls = true
        )
        coEvery { repository.updatePassword(any()) } returns flow {
            emit(
                ResultWrapper.Success(
                    "success"
                )
            )
        }
    }

    @Test
    fun `test update password live data`() {
        runTest {
            viewModel.updatePassword(UpdatePasswordRequest("123", "123", "123"))
            coVerify { repository.updatePassword(any()) }
            val result = viewModel.updatedPassword.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
            TestCase.assertTrue((result as ResultWrapper.Success<String>).payload == "success")
        }
    }
}
