package com.group4.gostudy.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSource
import com.group4.gostudy.data.local.datastore.UserPreferenceDataSourceImpl
import com.group4.gostudy.data.local.datastore.appDataStore
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSourceImpl
import com.group4.gostudy.data.network.api.service.AuthorizationInterceptor
import com.group4.gostudy.data.network.api.service.GoStudyApiService
import com.group4.gostudy.data.repository.ChapterRepository
import com.group4.gostudy.data.repository.ChapterRepositoryImpl
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.data.repository.CourseRepositoryImpl
import com.group4.gostudy.data.repository.DetailRepository
import com.group4.gostudy.data.repository.DetailRepositoryImpl
import com.group4.gostudy.data.repository.UserCourseRepository
import com.group4.gostudy.data.repository.UserCourseRepositoryImpl
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.data.repository.UserRepositoryImpl
import com.group4.gostudy.presentation.account.AccountViewModel
import com.group4.gostudy.presentation.account.changepassword.ChangePasswordViewModel
import com.group4.gostudy.presentation.account.historypayment.HistoryViewModel
import com.group4.gostudy.presentation.account.myprofile.MyProfileViewModel
import com.group4.gostudy.presentation.classes.ClassesViewModel
import com.group4.gostudy.presentation.course.CourseViewModel
import com.group4.gostudy.presentation.detail.DetailViewModel
import com.group4.gostudy.presentation.detail.material.MaterialViewModel
import com.group4.gostudy.presentation.detail.material.dialog.DialogOrderViewModel
import com.group4.gostudy.presentation.forgotpassword.ForgotPasswordViewModel
import com.group4.gostudy.presentation.home.HomeViewModel
import com.group4.gostudy.presentation.login.LoginViewModel
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.presentation.notification.NotificationViewModel
import com.group4.gostudy.presentation.otp.OtpViewModel
import com.group4.gostudy.presentation.payment.PaymentViewModel
import com.group4.gostudy.presentation.register.RegisterViewModel
import com.group4.gostudy.utils.AssetWrapper
import com.group4.gostudy.utils.PreferenceDataStoreHelper
import com.group4.gostudy.utils.PreferenceDataStoreHelperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { AuthorizationInterceptor(get()) }
        single { GoStudyApiService.invoke(get(), get()) }
    }

    private val localModule = module {
        single { androidContext().appDataStore }
        single<PreferenceDataStoreHelper> { PreferenceDataStoreHelperImpl(get()) }
    }

    private val dataSourceModule = module {
        single<GoStudyApiDataSource> { GoStudyApiDataSourceImpl(get()) }
        single<UserPreferenceDataSource> { UserPreferenceDataSourceImpl(get()) }
    }

    private val repositoryModule = module {
        single<UserRepository> { UserRepositoryImpl(get()) }
        single<CourseRepository> { CourseRepositoryImpl(get()) }
        single<ChapterRepository> { ChapterRepositoryImpl(get()) }
        single<DetailRepository> { DetailRepositoryImpl(get()) }
        single<UserCourseRepository> { UserCourseRepositoryImpl(get()) }
    }

    private val utilsModule = module {
        single { AssetWrapper(androidContext()) }
    }

    private val viewModelModule = module {
        viewModelOf(::NotificationViewModel)
        viewModelOf(::MyProfileViewModel)
        viewModelOf(::HistoryViewModel)
        viewModelOf(::MainViewModel)
        viewModelOf(::AccountViewModel)
        viewModelOf(::ChangePasswordViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::LoginViewModel)
        viewModelOf(::CourseViewModel)
        viewModelOf(::ClassesViewModel)
        viewModelOf(::RegisterViewModel)
        viewModelOf(::OtpViewModel)
        viewModelOf(::ForgotPasswordViewModel)
        viewModel { params -> PaymentViewModel(params.get(), get()) }
        viewModelOf(::DetailViewModel)
        viewModelOf(::MaterialViewModel)
        viewModelOf(::DialogOrderViewModel)
    }

    val modules: List<Module> = listOf(
        networkModule,
        localModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule,
        utilsModule
    )
}
