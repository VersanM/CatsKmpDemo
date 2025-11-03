package com.example.catskmpdemo.di

import com.example.catskmpdemo.common.data.HttpClientFactory
import com.example.catskmpdemo.feature.auth.data.DefaultAuthRepository
import com.example.catskmpdemo.feature.auth.domain.AuthRepository
import com.example.catskmpdemo.feature.auth.presentation.AuthViewModel
import com.example.catskmpdemo.feature.dashboard.data.datasource.CatDatasource
import com.example.catskmpdemo.feature.dashboard.data.datasource.CatDatasourceImpl
import com.example.catskmpdemo.feature.dashboard.data.repository.DefaultCatRepository
import com.example.catskmpdemo.feature.dashboard.domain.CatRepository
import com.example.catskmpdemo.feature.dashboard.presentation.cat_list.CatListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::CatDatasourceImpl).bind<CatDatasource>()
    singleOf(::DefaultCatRepository).bind<CatRepository>()
    singleOf(::DefaultAuthRepository).bind<AuthRepository>()

    viewModelOf(::CatListViewModel)
    viewModelOf(::AuthViewModel)
//    single { CatListViewModel(get()) }
//    single { AuthViewModel(get()) }
}