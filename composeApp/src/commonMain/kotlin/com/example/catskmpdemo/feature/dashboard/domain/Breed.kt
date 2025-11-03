package com.example.catskmpdemo.feature.dashboard.domain

data class Breed(
    val id: String,
    val name: String,
    val weightImperial: String?,
    val weightMetric: String?,
    val lifeSpan: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?
)
