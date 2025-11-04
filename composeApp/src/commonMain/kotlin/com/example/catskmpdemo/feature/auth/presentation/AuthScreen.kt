package com.example.catskmpdemo.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import catskmpdemo.composeapp.generated.resources.Res
import catskmpdemo.composeapp.generated.resources.email_label
import catskmpdemo.composeapp.generated.resources.login_action
import catskmpdemo.composeapp.generated.resources.login_title
import catskmpdemo.composeapp.generated.resources.password_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthScreenRoot(
    viewModel: AuthViewModel,
    onLoggedIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) onLoggedIn()
    }
    AuthScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AuthScreen(
    state: AuthState,
    onAction: (AuthAction) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                stringResource(Res.string.login_title),
                style = MaterialTheme.typography.headlineMedium
            )
            TextField(
                value = state.email,
                onValueChange = { onAction(AuthAction.OnEmailChange(it)) },
                label = { Text(stringResource(Res.string.email_label)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = state.password,
                onValueChange = { onAction(AuthAction.OnPasswordChange(it)) },
                label = { Text(stringResource(Res.string.password_label)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { onAction(AuthAction.OnLoginClick) },
                enabled = !state.isLoading && state.email.isNotBlank() && state.password.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(stringResource(Res.string.login_action))
                }
            }
            state.errorMessage?.let {
                Text(
                    it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                LaunchedEffect(Unit) { onAction(AuthAction.OnErrorConsumed) }
            }
        }
    }
}