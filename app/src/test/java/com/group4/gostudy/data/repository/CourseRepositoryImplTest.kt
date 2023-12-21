package com.group4.gostudy.data.repository

import app.cash.turbine.test
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.category.CategoriesResponse
import com.group4.gostudy.data.network.api.model.category.CategoryItemResponse
import com.group4.gostudy.data.network.api.model.category.DataCategories
import com.group4.gostudy.data.network.api.model.common.BaseResponse
import com.group4.gostudy.data.network.api.model.course.CourseItemResponse
import com.group4.gostudy.data.network.api.model.course.CoursesResponse
import com.group4.gostudy.data.network.api.model.course.DataCourses
import com.group4.gostudy.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CourseRepositoryImplTest {

    @MockK
    lateinit var datasource: GoStudyApiDataSource

    private lateinit var repository: CourseRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CourseRepositoryImpl(datasource)
    }

    @Test
    fun `getCategories result success`() {
        val mockResponse = CategoriesResponse(
            baseResponse = BaseResponse(
                message = "Get Categories Success",
                status = "Success"
            ),
            data = DataCategories(
                listOf(
                    CategoryItemResponse(
                        createdAt = null,
                        id = 0,
                        imageId = null,
                        imageUrl = null,
                        name = "Category 1",
                        slug = null,
                        updatedAt = null
                    ),
                    CategoryItemResponse(
                        createdAt = null,
                        id = 1,
                        imageId = null,
                        imageUrl = null,
                        name = null,
                        slug = null,
                        updatedAt = null
                    )
                )
            )
        )
        coEvery { datasource.getCategories() } returns mockResponse
        runTest {
            repository.getCategories().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.size, 2)
                TestCase.assertEquals(data.payload?.get(0)?.name, "Category 1")
                coVerify { datasource.getCategories() }
            }
        }
    }

    @Test
    fun `getCategories result loading`() {
        val mockResponse = CategoriesResponse(
            baseResponse = BaseResponse(
                message = "Get Categories Success",
                status = "Success"
            ),
            data = DataCategories(
                listOf(
                    CategoryItemResponse(
                        createdAt = null,
                        id = 0,
                        imageId = null,
                        imageUrl = null,
                        name = "Category 1",
                        slug = null,
                        updatedAt = null
                    ),
                    CategoryItemResponse(
                        createdAt = null,
                        id = 1,
                        imageId = null,
                        imageUrl = null,
                        name = null,
                        slug = null,
                        updatedAt = null
                    )
                )
            )
        )
        coEvery { datasource.getCategories() } returns mockResponse
        runTest {
            repository.getCategories().map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getCategories() }
            }
        }
    }

    @Test
    fun `getCategories result error`() {
        coEvery { datasource.getCategories() } throws IllegalStateException("mock error")
        runTest {
            repository.getCategories().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getCategories() }
            }
        }
    }

    @Test
    fun `getCourses result success`() {
        val mockResponse = CoursesResponse(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataCourses(
                listOf(
                    CourseItemResponse(
                        benefits = null,
                        category = null,
                        categoryId = null,
                        classCode = null,
                        courseBy = null,
                        createdAt = null,
                        createdBy = null,
                        description = null,
                        id = 0,
                        imageId = null,
                        imageUrl = null,
                        level = null,
                        name = "Course 1",
                        price = null,
                        totalDuration = null,
                        totalModule = null,
                        type = null,
                        updatedAt = null
                    ),
                    CourseItemResponse(
                        benefits = null,
                        category = null,
                        categoryId = null,
                        classCode = null,
                        courseBy = null,
                        createdAt = null,
                        createdBy = null,
                        description = null,
                        id = 1,
                        imageId = null,
                        imageUrl = null,
                        level = null,
                        name = "Course 2",
                        price = null,
                        totalDuration = null,
                        totalModule = null,
                        type = null,
                        updatedAt = null
                    )
                )
            )
        )
        coEvery { datasource.getCourses(any(), any(), any()) } returns mockResponse
        runTest {
            repository.getCourses("category", "search", "type").map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.size, 2)
                TestCase.assertEquals(data.payload?.get(0)?.name, "Course 1")
                coVerify { datasource.getCourses(any(), any(), any()) }
            }
        }
    }

    @Test
    fun `getCourses result loading`() {
        val mockResponse = CoursesResponse(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataCourses(
                listOf(
                    CourseItemResponse(
                        benefits = null,
                        category = null,
                        categoryId = null,
                        classCode = null,
                        courseBy = null,
                        createdAt = null,
                        createdBy = null,
                        description = null,
                        id = 0,
                        imageId = null,
                        imageUrl = null,
                        level = null,
                        name = "Course 1",
                        price = null,
                        totalDuration = null,
                        totalModule = null,
                        type = null,
                        updatedAt = null
                    ),
                    CourseItemResponse(
                        benefits = null,
                        category = null,
                        categoryId = null,
                        classCode = null,
                        courseBy = null,
                        createdAt = null,
                        createdBy = null,
                        description = null,
                        id = 1,
                        imageId = null,
                        imageUrl = null,
                        level = null,
                        name = "Course 2",
                        price = null,
                        totalDuration = null,
                        totalModule = null,
                        type = null,
                        updatedAt = null
                    )
                )
            )
        )
        coEvery { datasource.getCourses(any(), any(), any()) } returns mockResponse
        runTest {
            repository.getCourses("category", "search", "type").map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getCourses(any(), any(), any()) }
            }
        }
    }

    @Test
    fun `getCourses result error`() {
        coEvery { datasource.getCourses(any(), any(), any()) } throws IllegalStateException("mock error")
        runTest {
            repository.getCourses("category", "search", "type").map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getCourses(any(), any(), any()) }
            }
        }
    }
}
