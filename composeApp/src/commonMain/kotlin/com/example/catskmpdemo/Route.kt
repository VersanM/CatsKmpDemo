package com.example.catskmpdemo

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Auth: Route

    @Serializable
    data object CatList: Route

    @Serializable
    data class CatDetail(val id: String): Route
}