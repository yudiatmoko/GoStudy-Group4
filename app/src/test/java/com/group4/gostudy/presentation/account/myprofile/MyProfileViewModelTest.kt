package com.group4.gostudy.presentation.account.myprofile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MyProfileViewModelTest {

    @MockK
    private lateinit var repository: UserRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: MyProfileViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            MyProfileViewModel(repository)
        )
        coEvery { repository.getProfile() } returns flow {
            emit(
                ResultWrapper.Success(
                    mockk(relaxed = true)
                )
            )
        }
        coEvery { repository.updateProfile(any(), any(), any(), any(), any()) } returns flow {
            emit(
                ResultWrapper.Success(
                    mockk(relaxed = true)
                )
            )
        }
    }

    @Test
    fun `test profile live data`() {
        runTest {
            viewModel.updateProfile("test".toRequestBody(), "test".toRequestBody(), "test".toRequestBody(), "test".toRequestBody(), MultipartBody.Part.create("test".toRequestBody()))
            coVerify { repository.updateProfile(any(), any(), any(), any(), any()) }
            val result = viewModel.profile.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
        }
    }
}
