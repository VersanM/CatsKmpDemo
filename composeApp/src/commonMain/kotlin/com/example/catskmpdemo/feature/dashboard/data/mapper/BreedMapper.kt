package com.example.catskmpdemo.feature.dashboard.data.mapper

import com.example.catskmpdemo.feature.dashboard.data.dto.BreedDto
import com.example.catskmpdemo.feature.dashboard.domain.Breed

fun BreedDto.toDomain() = Breed(
    id = this.id,
    name = this.name,
    weightImperial = this.weight?.imperial,
    weightMetric = this.weight?.metric,
    lifeSpan = this.lifeSpan,
    temperament = this.temperament,
    origin = this.origin,
    description = this.description
)