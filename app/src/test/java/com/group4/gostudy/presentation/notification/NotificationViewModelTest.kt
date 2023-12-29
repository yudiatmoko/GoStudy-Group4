package com.group4.gostudy.presentation.notification

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Notification
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

class NotificationViewModelTest {

    @MockK
    private lateinit var repository: CourseRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: NotificationViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            NotificationViewModel(repository),
            recordPrivateCalls = true
        )
        coEvery { repository.getNotifications() } returns flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        mockk(relaxed = true),
                        mockk(relaxed = true),
                        mockk(relaxed = true),
                        mockk(relaxed = true)
                    )
                )
            )
        }
    }

    @Test
    fun `test notification live data`() {
        runTest {
            viewModel.getNotification()
            coVerify { repository.getNotifications() }
            val result = viewModel.notifications.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
            TestCase.assertTrue((result as ResultWrapper.Success<List<Notification>>).payload?.size == 4)
        }
    }
}
