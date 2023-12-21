package com.group4.gostudy.data.local.datastore

import app.cash.turbine.test
import com.group4.gostudy.utils.PreferenceDataStoreHelper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserPreferenceDataSourceImplTest {

    @MockK
    lateinit var preferenceDataStoreHelper: PreferenceDataStoreHelper

    private lateinit var userPreferenceDataSource: UserPreferenceDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userPreferenceDataSource = UserPreferenceDataSourceImpl(preferenceDataStoreHelper)
    }

    @Test
    fun getUserTokenFlow() {
        runTest {
            coEvery { preferenceDataStoreHelper.getPreference(any(), "") } returns flow { emit("token") }
            userPreferenceDataSource.getUserTokenFlow().test {
                val itemResult = awaitItem()
                TestCase.assertEquals("token", itemResult)
                awaitComplete()
            }
        }
    }

    @Test
    fun setUserToken() {
        runTest {
            coEvery { preferenceDataStoreHelper.putPreference(any(), "token") } returns Unit
            val result = userPreferenceDataSource.setUserToken("token")
            coVerify { preferenceDataStoreHelper.putPreference(any(), "token") }
            TestCase.assertEquals(result, Unit)
        }
    }

    @Test
    fun getUserToken() {
        runTest {
            coEvery { preferenceDataStoreHelper.getFirstPreference(any(), "") } returns "token"
            val result = userPreferenceDataSource.getUserToken()
            coVerify { preferenceDataStoreHelper.getFirstPreference(any(), "") }
            TestCase.assertEquals(result, "token")
        }
    }
}
