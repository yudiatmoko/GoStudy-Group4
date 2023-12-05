package com.group4.gostudy.data.network.api.service

import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class AuthorizationInterceptor(
    private val userPreferenceDataSource: UserPreferenceDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = runBlocking {
            userPreferenceDataSource.getUserTokenFlow().first()
        }
        val request = chain.request()
        val authenticatedRequest = if (authToken.isNotEmpty()) {
            request.newBuilder()
                .header("Authorization", "Bearer $authToken")
                .build()
        } else {
            request
        }
        return chain.proceed(authenticatedRequest)
    }
}
