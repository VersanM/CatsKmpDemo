package com.example.catskmpdemo.feature.auth.presentation

sealed interface AuthAction {
    data class OnEmailChange(val value: String) : AuthAction
    data class OnPasswordChange(val value: String) : AuthAction
    data object OnLoginClick : AuthAction
    data object OnErrorConsumed : AuthAction
}