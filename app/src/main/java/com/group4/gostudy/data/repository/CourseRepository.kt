package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.category.toCategoryList
import com.group4.gostudy.data.network.api.model.coursev2.toChapterList
import com.group4.gostudy.data.network.api.model.coursev2.toCourse
import com.group4.gostudy.data.network.api.model.coursev2.toCourseList
import com.group4.gostudy.data.network.api.model.historypayment.toHistoryPaymentList
import com.group4.gostudy.data.network.api.model.notification.toAllNotifList
import com.group4.gostudy.data.network.api.model.payment.PaymentRequest
import com.group4.gostudy.data.network.api.model.payment.toPayment
import com.group4.gostudy.model.AllNotif
import com.group4.gostudy.model.Category
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.HistoryPayment
import com.group4.gostudy.model.Payment
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface CourseRepository {
    suspend fun getCategories(): Flow<ResultWrapper<List<Category>>>

    suspend fun getCourses(
        category: String?,
        search: String?,
        type: String?,
        level: String?,
        promoPrecentage: Boolean?,
        createAt: Boolean?,
        rating: Boolean?
    ): Flow<ResultWrapper<List<Course>>>

    suspend fun getCourseById(id: Int): Flow<ResultWrapper<Course>>
    suspend fun getChaptersV2(id: Int): Flow<ResultWrapper<List<Chapter>>>
    suspend fun getHistoryPayments(): Flow<ResultWrapper<List<HistoryPayment>>>
    suspend fun order(paymentRequest: PaymentRequest): Flow<ResultWrapper<Payment>>
    suspend fun getNotifications(): Flow<ResultWrapper<List<AllNotif>>>
}

class CourseRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : CourseRepository {
    override suspend fun getCategories(): Flow<ResultWrapper<List<Category>>> {
        return proceedFlow {
            apiDataSource.getCategories().data?.categories?.toCategoryList() ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getCourses(
        category: String?,
        search: String?,
        type: String?,
        level: String?,
        promoPrecentage: Boolean?,
        createAt: Boolean?,
        rating: Boolean?
    ): Flow<ResultWrapper<List<Course>>> {
        return proceedFlow {
            apiDataSource.getCourses(
                category,
                search,
                type,
                level,
                promoPrecentage,
                createAt,
                rating
            ).data?.courses?.toCourseList() ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getCourseById(id: Int): Flow<ResultWrapper<Course>> {
        return proceedFlow {
            apiDataSource.getCourseById(id).data.course.toCourse()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getChaptersV2(id: Int): Flow<ResultWrapper<List<Chapter>>> {
        return proceedFlow {
            apiDataSource.getCourseById(id).data.course.chapters?.toChapterList() ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun order(paymentRequest: PaymentRequest): Flow<ResultWrapper<Payment>> {
        return proceedFlow {
            apiDataSource.createOrder(paymentRequest).data.createPayment.toPayment()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getHistoryPayments(): Flow<ResultWrapper<List<HistoryPayment>>> {
        return proceedFlow {
            apiDataSource.getHistoryPayments().data?.historyPayment?.toHistoryPaymentList() ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getNotifications(): Flow<ResultWrapper<List<AllNotif>>> {
        return proceedFlow {
            apiDataSource.getNotifications().data?.allNotif?.toAllNotifList() ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }
}
