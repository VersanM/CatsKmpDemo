package com.example.catskmpdemo.feature.dashboard.data.repository

import com.example.catskmpdemo.common.domain.DataError
import com.example.catskmpdemo.feature.dashboard.data.datasource.CatDatasource
import com.example.catskmpdemo.feature.dashboard.domain.CatRepository
import com.example.catskmpdemo.common.domain.Result
import com.example.catskmpdemo.common.domain.mapList
import com.example.catskmpdemo.feature.dashboard.data.mapper.toDomain
import com.example.catskmpdemo.feature.dashboard.domain.Cat

class DefaultCatRepository(
    private val catRemoteDataSource: CatDatasource
) : CatRepository {
    override suspend fun getCats(): Result<List<Cat>, DataError.Remote> =
        catRemoteDataSource
            .getCats()
            .mapList { it.toDomain() }
}