package com.example.catskmpdemo.feature.dashboard.domain

data class Cat(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<Breed>
)

