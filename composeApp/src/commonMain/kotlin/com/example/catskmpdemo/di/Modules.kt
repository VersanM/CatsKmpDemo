package com.example.catskmpdemo.di

import com.example.catskmpdemo.common.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
//    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
//    singleOf(::DefaultBookRepository).bind<BookRepository>()

//    viewModelOf(::CatListViewModel)
//    viewModelOf(::CatDetailViewModel)
}