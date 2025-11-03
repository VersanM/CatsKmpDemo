package com.example.catskmpdemo.feature.dashboard.data.mapper

import com.example.catskmpdemo.feature.dashboard.data.dto.CatDto
import com.example.catskmpdemo.feature.dashboard.domain.Cat

fun CatDto.toDomain() = Cat(
    id = this.id,
    url = this.url,
    width = this.width,
    height = this.height,
    breeds = this.breeds.map { it.toDomain() }
)