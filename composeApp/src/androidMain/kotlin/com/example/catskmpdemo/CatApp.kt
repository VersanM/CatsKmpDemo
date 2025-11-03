package com.example.catskmpdemo

import android.app.Application
import com.example.catskmpdemo.di.initKoin
import org.koin.android.ext.koin.androidContext

class CatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CatApp)
        }
    }
}