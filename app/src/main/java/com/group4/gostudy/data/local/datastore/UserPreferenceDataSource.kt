package com.group4.gostudy.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import com.group4.gostudy.utils.PreferenceDataStoreHelper
import kotlinx.coroutines.flow.Flow

interface UserPreferenceDataSource {
    fun getUserTokenFlow(): Flow<String>
    suspend fun setUserToken(token: String)
    suspend fun getUserToken(): String

    suspend fun removeUserToken()
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

    override suspend fun setUserToken(
        token: String
    ) {
        preferenceDataStoreHelper.putPreference(
            USER_TOKEN_PREF,
            token
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

    companion object {
        val USER_TOKEN_PREF = stringPreferencesKey("USER_TOKEN_PREF")
    }
}
