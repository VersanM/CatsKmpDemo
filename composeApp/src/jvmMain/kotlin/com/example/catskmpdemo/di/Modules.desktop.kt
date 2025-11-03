package com.example.catskmpdemo.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Java.create() }
    }