package com.group4.gostudy.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
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

class HomeViewModelTest {

    @MockK
    private lateinit var repository: CourseRepository

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(
        UnconfinedTestDispatcher()
    )

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(
            HomeViewModel(repository),
            recordPrivateCalls = true
        )
        coEvery { repository.getCourses(any(), any(), any()) } returns flow {
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
        coEvery { repository.getCategories() } returns flow {
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
    fun `test courses live data`() {
        runTest {
            viewModel.getCourse("all", "search", "type")
            coVerify { repository.getCourses(any(), any(), any()) }
            val result = viewModel.courses.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
            TestCase.assertTrue((result as ResultWrapper.Success<List<Course>>).payload?.size == 4)
        }
    }

    @Test
    fun `test categories live data`() {
        runTest {
            viewModel.getCategory()
            coVerify { repository.getCategories() }
            val result = viewModel.categories.getOrAwaitValue()
            TestCase.assertTrue(result is ResultWrapper.Success)
            TestCase.assertTrue((result as ResultWrapper.Success<List<Course>>).payload?.size == 4)
        }
    }
}
