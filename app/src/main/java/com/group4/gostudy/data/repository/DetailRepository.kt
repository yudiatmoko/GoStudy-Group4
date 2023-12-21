package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
// import com.group4.gostudy.data.network.api.model.course.toCourseList
import com.group4.gostudy.data.network.api.model.detail.toCourseList
import com.group4.gostudy.model.DetailCourse
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

interface DetailRepository {
    suspend fun getCourseID(
        category: String?,
        module: String?,
        chapter: String?
    ): Flow<ResultWrapper<List<DetailCourse>>>
}

class DetailRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : DetailRepository {
    /* override suspend fun getModule(): Flow<ResultWrapper<List<Module>>> {
         return proceedFlow {
             apiDataSource.getModules().dataModules?.module?.toModuleList() ?: emptyList()
         }.catch {
             emit(ResultWrapper.Error(Exception(it)))
         }.onStart {
             emit(ResultWrapper.Loading())
             delay(2000)
         }
     }*/
    override suspend fun getCourseID(
        category: String?,
        module: String?,
        chapter: String?
    ): Flow<ResultWrapper<List<DetailCourse>>> {
        return proceedFlow {
            apiDataSource.getCourseId(category, module, chapter).data?.course?.toCourseList()
                ?: emptyList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }
    }
}
