package com.example.catskmpdemo

import androidx.compose.ui.window.ComposeUIViewController
import com.example.catskmpdemo.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }