package com.example.catskmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform