package com.example.catskmpdemo.feature.dashboard.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeightDto(
    @SerialName("imperial") val imperial: String? = null,
    @SerialName("metric") val metric: String? = null,
)

@Serializable
data class BreedDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("weight") val weight: WeightDto? = null,
    @SerialName("life_span") val lifeSpan: String? = null,
    @SerialName("temperament") val temperament: String? = null,
    @SerialName("origin") val origin: String? = null,
    @SerialName("description") val description: String? = null,
)
