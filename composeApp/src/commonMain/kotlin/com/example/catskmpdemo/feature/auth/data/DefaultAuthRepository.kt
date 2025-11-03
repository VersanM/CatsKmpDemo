package com.example.catskmpdemo.feature.auth.data

import com.example.catskmpdemo.common.domain.DataError
import com.example.catskmpdemo.common.domain.Result
import com.example.catskmpdemo.feature.auth.domain.AuthRepository
import com.example.catskmpdemo.feature.auth.domain.User
import kotlinx.coroutines.delay

class DefaultAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User, DataError.Remote> {
        delay(1000)
        return if (email.isNotBlank() && password.isNotBlank()) {
            Result.Success(User(email))
        } else {
            Result.Error(DataError.Remote.UNKNOWN)
        }
    }
}