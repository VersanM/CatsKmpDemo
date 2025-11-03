package com.example.catskmpdemo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.catskmpdemo.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "CatsKmpDemo",
    ) {
        App()
    }
}