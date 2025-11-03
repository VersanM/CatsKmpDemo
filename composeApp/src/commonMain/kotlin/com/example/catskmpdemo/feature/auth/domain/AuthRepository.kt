package com.example.catskmpdemo.feature.auth.domain

import com.example.catskmpdemo.common.domain.DataError
import com.example.catskmpdemo.common.domain.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User, DataError.Remote>
}