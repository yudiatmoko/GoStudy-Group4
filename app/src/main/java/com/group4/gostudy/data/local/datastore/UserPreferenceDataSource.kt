package com.group4.gostudy.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.group4.gostudy.utils.PreferenceDataStoreHelper
import kotlinx.coroutines.flow.Flow

interface UserPreferenceDataSource {
    fun getUserTokenFlow(): Flow<String>
    fun getUserBoardingFlow(): Flow<Boolean>
    suspend fun setUserToken(token: String)
    suspend fun setBoarding(isBoarding: Boolean)
    suspend fun getUserToken(): String
    suspend fun removeUserToken()
    suspend fun isFromBoarding(): Boolean
}

class UserPreferenceDataSourceImpl(
    private val preferenceDataStoreHelper: PreferenceDataStoreHelper
) : UserPreferenceDataSource {
    override fun getUserTokenFlow(): Flow<String> {
        return preferenceDataStoreHelper.getPreference(
            USER_TOKEN_PREF,
            ""
        )
    }

    override fun getUserBoardingFlow(): Flow<Boolean> {
        return preferenceDataStoreHelper.getPreference(
            BOARDING_PASS,
            false
        )
    }

    override suspend fun setUserToken(
        token: String
    ) {
        preferenceDataStoreHelper.putPreference(
            USER_TOKEN_PREF,
            token
        )
    }

    override suspend fun setBoarding(isBoarding: Boolean) {
        return preferenceDataStoreHelper.putPreference(
            BOARDING_PASS,
            true
        )
    }

    override suspend fun getUserToken(): String {
        return preferenceDataStoreHelper.getFirstPreference(
            USER_TOKEN_PREF,
            ""
        )
    }

    override suspend fun removeUserToken() {
        return preferenceDataStoreHelper.removePreference(USER_TOKEN_PREF)
    }

    override suspend fun isFromBoarding(): Boolean {
        return preferenceDataStoreHelper.getFirstPreference(
            BOARDING_PASS,
            false
        )
    }

    companion object {
        val USER_TOKEN_PREF = stringPreferencesKey("USER_TOKEN_PREF")
        val BOARDING_PASS = booleanPreferencesKey("BOARDING_PASS")
    }
}
