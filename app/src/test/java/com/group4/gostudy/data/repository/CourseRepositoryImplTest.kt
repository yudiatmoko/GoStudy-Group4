package com.group4.gostudy.data.repository

import app.cash.turbine.test
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.category.CategoriesResponse
import com.group4.gostudy.data.network.api.model.category.CategoryItemResponse
import com.group4.gostudy.data.network.api.model.category.DataCategories
import com.group4.gostudy.data.network.api.model.common.BaseResponse
import com.group4.gostudy.data.network.api.model.coursebyid.CourseByIdResponse
import com.group4.gostudy.data.network.api.model.coursebyid.DataCourseById
import com.group4.gostudy.data.network.api.model.coursev2.ChapterItemResponseV2
import com.group4.gostudy.data.network.api.model.coursev2.CourseItemResponseV2
import com.group4.gostudy.data.network.api.model.coursev2.CoursesResponseV2
import com.group4.gostudy.data.network.api.model.coursev2.DataCoursesResponseV2
import com.group4.gostudy.data.network.api.model.historypayment.DataHistoryPayments
import com.group4.gostudy.data.network.api.model.historypayment.HistoryPaymentItemResponse
import com.group4.gostudy.data.network.api.model.historypayment.HistoryPaymentsResponse
import com.group4.gostudy.data.network.api.model.notification.AllNotifItemResponse
import com.group4.gostudy.data.network.api.model.notification.DataNotificationsResponse
import com.group4.gostudy.data.network.api.model.notification.NotificationItemResponse
import com.group4.gostudy.data.network.api.model.notification.NotificationsResponse
import com.group4.gostudy.data.network.api.model.payment.DataPaymentResponse
import com.group4.gostudy.data.network.api.model.payment.PaymentItemResponse
import com.group4.gostudy.data.network.api.model.payment.PaymentRequest
import com.group4.gostudy.data.network.api.model.payment.PaymentResponse
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
        val mockResponse = CoursesResponseV2(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataCoursesResponseV2(
                listOf(
                    CourseItemResponseV2(
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
                        updatedAt = null,
                        chapters = null,
                        rating = null,
                        promoPercentage = null
                    ),
                    CourseItemResponseV2(
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
                        updatedAt = null,
                        chapters = null,
                        rating = null,
                        promoPercentage = null
                    )
                )
            )
        )
        coEvery { datasource.getCourses(any(), any(), any(), any(), any(), any(), any()) } returns mockResponse
        runTest {
            repository.getCourses("category", "search", "type", "level", false, false, false).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.size, 2)
                TestCase.assertEquals(data.payload?.get(0)?.name, "Course 1")
                coVerify { datasource.getCourses(any(), any(), any(), any(), any(), any(), any()) }
            }
        }
    }

    @Test
    fun `getCourses result loading`() {
        val mockResponse = CoursesResponseV2(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataCoursesResponseV2(
                listOf(
                    CourseItemResponseV2(
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
                        updatedAt = null,
                        chapters = null,
                        rating = null,
                        promoPercentage = null
                    ),
                    CourseItemResponseV2(
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
                        updatedAt = null,
                        chapters = null,
                        rating = null,
                        promoPercentage = null
                    )
                )
            )
        )
        coEvery { datasource.getCourses(any(), any(), any(), any(), any(), any(), any()) } returns mockResponse
        runTest {
            repository.getCourses("category", "search", "type", "level", false, false, false).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getCourses(any(), any(), any(), any(), any(), any(), any()) }
            }
        }
    }

    @Test
    fun `getCourses result error`() {
        val mockResponse = CoursesResponseV2(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataCoursesResponseV2(
                listOf(
                    CourseItemResponseV2(
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
                        updatedAt = null,
                        chapters = null,
                        rating = null,
                        promoPercentage = null
                    ),
                    CourseItemResponseV2(
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
                        updatedAt = null,
                        chapters = null,
                        rating = null,
                        promoPercentage = null
                    )
                )
            )
        )
        coEvery { datasource.getCourses(any(), any(), any(), any(), any(), any(), any()) } throws IllegalStateException("mock error")
        runTest {
            repository.getCourses("category", "search", "type", "level", false, false, false).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getCourses(any(), any(), any(), any(), any(), any(), any()) }
            }
        }
    }

    @Test
    fun `getCoursesById result success`() {
        val mockResponse = CourseByIdResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataCourseById(
                CourseItemResponseV2(
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
                    updatedAt = null,
                    chapters = null,
                    rating = null,
                    promoPercentage = null
                )
            )
        )
        coEvery { datasource.getCourseById(any()) } returns mockResponse
        runTest {
            repository.getCourseById(0).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.name, "Course 1")
                coVerify { datasource.getCourseById(any()) }
            }
        }
    }

    @Test
    fun `getCoursesById result loading`() {
        val mockResponse = CourseByIdResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataCourseById(
                CourseItemResponseV2(
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
                    updatedAt = null,
                    chapters = null,
                    rating = null,
                    promoPercentage = null
                )
            )
        )
        coEvery { datasource.getCourseById(any()) } returns mockResponse
        runTest {
            repository.getCourseById(0).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getCourseById(any()) }
            }
        }
    }

    @Test
    fun `getCoursesById result error`() {
        coEvery { datasource.getCourseById(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.getCourseById(0).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getCourseById(any()) }
            }
        }
    }

    @Test
    fun `getChaptersV2 result success`() {
        val mockResponse = CourseByIdResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataCourseById(
                CourseItemResponseV2(
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
                    updatedAt = null,
                    chapters = listOf(
                        ChapterItemResponseV2(
                            courseId = null,
                            createdAt = null,
                            id = null,
                            modules = null,
                            name = "Chapter 1",
                            noChapter = null,
                            totalDuration = null,
                            totalModule = null,
                            updatedAt = null
                        )
                    ),
                    rating = null,
                    promoPercentage = null
                )
            )
        )
        coEvery { datasource.getCourseById(any()) } returns mockResponse
        runTest {
            repository.getChaptersV2(0).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.size, 1)
                coVerify { datasource.getCourseById(any()) }
            }
        }
    }

    @Test
    fun `getChaptersV2 result loading`() {
        val mockResponse = CourseByIdResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataCourseById(
                CourseItemResponseV2(
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
                    updatedAt = null,
                    chapters = listOf(
                        ChapterItemResponseV2(
                            courseId = null,
                            createdAt = null,
                            id = null,
                            modules = null,
                            name = "Chapter 1",
                            noChapter = null,
                            totalDuration = null,
                            totalModule = null,
                            updatedAt = null
                        )
                    ),
                    rating = null,
                    promoPercentage = null
                )
            )
        )
        coEvery { datasource.getCourseById(any()) } returns mockResponse
        runTest {
            repository.getChaptersV2(0).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getCourseById(any()) }
            }
        }
    }

    @Test
    fun `getChaptersV2 result error`() {
        coEvery { datasource.getCourseById(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.getChaptersV2(0).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getCourseById(any()) }
            }
        }
    }

    @Test
    fun `getHistoryPayment result success`() {
        val mockResponse = HistoryPaymentsResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataHistoryPayments(
                listOf(
                    HistoryPaymentItemResponse(
                        course = CourseItemResponseV2(
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
                            updatedAt = null,
                            chapters = null,
                            rating = null,
                            promoPercentage = null
                        ),
                        createdAt = null,
                        orderId = null,
                        price = 250000,
                        status = null
                    )
                )
            )
        )
        coEvery { datasource.getHistoryPayments() } returns mockResponse
        runTest {
            repository.getHistoryPayments().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.get(0)?.price, 250000)
                coVerify { datasource.getHistoryPayments() }
            }
        }
    }

    @Test
    fun `getHistoryPayment result loading`() {
        val mockResponse = HistoryPaymentsResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataHistoryPayments(
                listOf(
                    HistoryPaymentItemResponse(
                        course = CourseItemResponseV2(
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
                            updatedAt = null,
                            chapters = null,
                            rating = null,
                            promoPercentage = null
                        ),
                        createdAt = null,
                        orderId = null,
                        price = 250000,
                        status = null
                    )
                )
            )
        )
        coEvery { datasource.getHistoryPayments() } returns mockResponse
        runTest {
            repository.getHistoryPayments().map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getHistoryPayments() }
            }
        }
    }

    @Test
    fun `getHistoryPayment result error`() {
        coEvery { datasource.getHistoryPayments() } throws IllegalStateException("mock error")
        runTest {
            repository.getHistoryPayments().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getHistoryPayments() }
            }
        }
    }

    @Test
    fun `getNotifications result success`() {
        val mockResponse = NotificationsResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataNotificationsResponse(
                listOf(
                    AllNotifItemResponse(
                        createdAt = null,
                        id = null,
                        isRead = true,
                        notifId = null,
                        notification = NotificationItemResponse(
                            category = null,
                            createdAt = null,
                            description = null,
                            id = null,
                            title = null,
                            updatedAt = null
                        ),
                        updatedAt = null,
                        userId = null
                    )
                )
            )
        )
        coEvery { datasource.getNotifications() } returns mockResponse
        runTest {
            repository.getNotifications().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.get(0)?.isRead, true)
                coVerify { datasource.getNotifications() }
            }
        }
    }

    @Test
    fun `getNotifications result loading`() {
        val mockResponse = NotificationsResponse(
            message = "Get Courses Success",
            status = "Success",
            data = DataNotificationsResponse(
                listOf(
                    AllNotifItemResponse(
                        createdAt = null,
                        id = null,
                        isRead = true,
                        notifId = null,
                        notification = NotificationItemResponse(
                            category = null,
                            createdAt = null,
                            description = null,
                            id = null,
                            title = null,
                            updatedAt = null
                        ),
                        updatedAt = null,
                        userId = null
                    )
                )
            )
        )
        coEvery { datasource.getNotifications() } returns mockResponse
        runTest {
            repository.getNotifications().map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.getNotifications() }
            }
        }
    }

    @Test
    fun `getNotifications result error`() {
        coEvery { datasource.getNotifications() } throws IllegalStateException("mock error")
        runTest {
            repository.getNotifications().map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.getNotifications() }
            }
        }
    }

    @Test
    fun `order result success`() {
        val mockResponse = PaymentResponse(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataPaymentResponse(
                createPayment = PaymentItemResponse(
                    courseId = null,
                    createdAt = null,
                    id = null,
                    orderId = null,
                    paymentType = null,
                    price = 250000,
                    redirect_url = null,
                    settlementTime = null,
                    status = null,
                    token = null,
                    updatedAt = null,
                    userId = null
                )
            )
        )
        coEvery { datasource.createOrder(any()) } returns mockResponse
        runTest {
            repository.order(paymentRequest = PaymentRequest(0)).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Success)
                TestCase.assertEquals(data.payload?.price, 250000)
                coVerify { datasource.createOrder(any()) }
            }
        }
    }

    @Test
    fun `order result loading`() {
        val mockResponse = PaymentResponse(
            baseResponse = BaseResponse(
                message = "Get Courses Success",
                status = "Success"
            ),
            data = DataPaymentResponse(
                createPayment = PaymentItemResponse(
                    courseId = null,
                    createdAt = null,
                    id = null,
                    orderId = null,
                    paymentType = null,
                    price = 250000,
                    redirect_url = null,
                    settlementTime = null,
                    status = null,
                    token = null,
                    updatedAt = null,
                    userId = null
                )
            )
        )
        coEvery { datasource.createOrder(any()) } returns mockResponse
        runTest {
            repository.order(paymentRequest = PaymentRequest(0)).map {
                delay(100)
                it
            }.test {
                delay(2220)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Loading)
                coVerify { datasource.createOrder(any()) }
            }
        }
    }

    @Test
    fun `order result error`() {
        coEvery { datasource.createOrder(any()) } throws IllegalStateException("mock error")
        runTest {
            repository.order(paymentRequest = PaymentRequest(0)).map {
                delay(100)
                it
            }.test {
                delay(2320)
                val data = expectMostRecentItem()
                TestCase.assertTrue(data is ResultWrapper.Error)
                coVerify { datasource.createOrder(any()) }
            }
        }
    }
}
