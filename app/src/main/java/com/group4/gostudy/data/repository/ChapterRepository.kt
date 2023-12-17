package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.chapter.toChapterList
import com.group4.gostudy.data.network.api.model.module.toModuleList
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.model.Module
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

interface ChapterRepository {
    suspend fun getChapters(): Flow<ResultWrapper<List<Chapter>>>
    suspend fun getModules(): Flow<ResultWrapper<List<Module>>>
}

class ChapterRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : ChapterRepository {
    override suspend fun getChapters(): Flow<ResultWrapper<List<Chapter>>> {
        return proceedFlow {
            apiDataSource.getChapter().data.chapters.toChapterList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

    override suspend fun getModules(): Flow<ResultWrapper<List<Module>>> {
        return proceedFlow {
            apiDataSource.getModules().data.modules.toModuleList()
        }.catch {
            emit(ResultWrapper.Error(Exception(it)))
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }
}
