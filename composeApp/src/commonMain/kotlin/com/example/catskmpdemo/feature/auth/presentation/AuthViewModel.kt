package com.example.catskmpdemo.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catskmpdemo.common.domain.onError
import com.example.catskmpdemo.common.domain.onSuccess
import com.example.catskmpdemo.feature.auth.domain.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.OnEmailChange ->
                _state.update { it.copy(email = action.value) }

            is AuthAction.OnPasswordChange ->
                _state.update { it.copy(password = action.value) }

            AuthAction.OnLoginClick -> login()
            AuthAction.OnErrorConsumed ->
                _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun login() {
        val current = _state.value
        if (current.isLoading) return
        _state.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            authRepository.login(current.email, current.password)
                .onSuccess {
                    _state.update { it.copy(isLoading = false, isLoggedIn = true) }
                }
                .onError { e ->
                    _state.update { it.copy(isLoading = false, errorMessage = e.name) }
                }
        }
    }

    private inline fun MutableStateFlow<AuthState>.update(block: (AuthState) -> AuthState) {
        value = block(value)
    }
}