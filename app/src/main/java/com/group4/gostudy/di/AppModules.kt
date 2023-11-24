package com.group4.gostudy.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSourceImpl
import com.group4.gostudy.data.network.api.service.GoStudyApiService
import com.group4.gostudy.data.repository.NotificationRepository
import com.group4.gostudy.data.repository.NotificationRepositoryImpl
import com.group4.gostudy.data.repository.ProfileRepository
import com.group4.gostudy.data.repository.ProfileRepositoryImpl
import com.group4.gostudy.presentation.account.myprofile.MyProfileViewModel
import com.group4.gostudy.presentation.notification.NotificationViewModel
import com.group4.gostudy.utils.AssetWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { GoStudyApiService.invoke(get()) }
    }

    private val dataSourceModule = module {
        single<GoStudyApiDataSource> { GoStudyApiDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<NotificationRepository> { NotificationRepositoryImpl(get()) }
        single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    }

    private val utilsModule = module {
        single { AssetWrapper(androidContext()) }
    }

    private val viewModelModule = module {
        viewModelOf(::NotificationViewModel)
        viewModelOf(::MyProfileViewModel)
    }

    val modules: List<Module> = listOf(
        networkModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule,
        utilsModule
    )
}
