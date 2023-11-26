package com.group4.gostudy.data.repository

import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.history.toHistoryList
import com.group4.gostudy.model.History
import com.group4.gostudy.utils.ResultWrapper
import com.group4.gostudy.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

interface HistoryRepository {
    suspend fun getHistories(): Flow<ResultWrapper<List<History>>>
}

class HistoryRepositoryImpl(
    private val apiDataSource: GoStudyApiDataSource
) : HistoryRepository {
    override suspend fun getHistories(): Flow<ResultWrapper<List<History>>> {
        return proceedFlow {
            apiDataSource.getHistories().data?.toHistoryList() ?: emptyList()
        }
    }
}
