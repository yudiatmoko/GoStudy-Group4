package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.coursev2.toCourse
import com.group4.gostudy.data.network.api.model.usercoursev2.toCourseList
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.UserCourse
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

interface UserCourseRepository {
    suspend fun getUserCourse(type: String?, search: String?): Flow<ResultWrapper<List<UserCourse>>>
    suspend fun getUserCourseById(id: Int): Flow<ResultWrapper<Course?>>
}

class UserCourseRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : UserCourseRepository {
    override suspend fun getUserCourse(type: String?, search: String?): Flow<ResultWrapper<List<UserCourse>>> {
        return proceedFlow {
            apiDataSource.getUserCourse(type, search).data?.course?.toCourseList() ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getUserCourseById(id: Int): Flow<ResultWrapper<Course?>> {
        return proceedFlow {
            apiDataSource.getUserCourseById(id).data?.course?.toCourse()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }
}
