package com.example.catskmpdemo.feature.dashboard.domain

import com.example.catskmpdemo.common.domain.DataError
import com.example.catskmpdemo.common.domain.Result

interface CatRepository {
    suspend fun getCats(): Result<List<Cat>, DataError.Remote>
}