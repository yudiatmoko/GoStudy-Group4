package com.group4.gostudy.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import com.group4.gostudy.utils.PreferenceDataStoreHelper
import kotlinx.coroutines.flow.Flow

interface UserPreferenceDataSource {
    fun getUserTokenFlow(): Flow<String>
    fun getNonLoginFlow(): Flow<String>
    suspend fun setUserToken(token: String)
    suspend fun setNonLogin(loginAsGuest: String)
    suspend fun getUserToken(): String
    suspend fun removeUserToken()
    suspend fun removeNonLogin()
    suspend fun getNonLogin(): String
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

    override fun getNonLoginFlow(): Flow<String> {
        return preferenceDataStoreHelper.getPreference(
            NON_LOGIN_PREF,
            NonLoginMode.FIRST_INSTALL.value
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

    override suspend fun setNonLogin(loginAsGuest: String) {
        return preferenceDataStoreHelper.putPreference(
            NON_LOGIN_PREF,
            loginAsGuest
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

    override suspend fun removeNonLogin() {
        return preferenceDataStoreHelper.removePreference(NON_LOGIN_PREF)
    }

    override suspend fun getNonLogin(): String {
        return preferenceDataStoreHelper.getFirstPreference(
            NON_LOGIN_PREF,
            NonLoginMode.FIRST_INSTALL.value
        )
    }

    companion object {
        val USER_TOKEN_PREF = stringPreferencesKey("USER_TOKEN_PREF")
        val NON_LOGIN_PREF = stringPreferencesKey("NON_LOGIN_PREF")
    }
}
