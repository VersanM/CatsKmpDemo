package com.example.catskmpdemo

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catskmpdemo.feature.auth.presentation.AuthScreenRoot
import com.example.catskmpdemo.feature.auth.presentation.AuthViewModel
import com.example.catskmpdemo.feature.dashboard.presentation.cat_list.CatListScreenRoot
import com.example.catskmpdemo.feature.dashboard.presentation.cat_list.CatListViewModel
import com.example.catskmpdemo.ui.CatsAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    CatsAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) { // ensure background applied
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.Auth
            ) {
                composable<Route.Auth> {
                    val viewModel = koinViewModel<AuthViewModel>()

                    AuthScreenRoot(
                        viewModel = viewModel,
                        onLoggedIn = {
                            navController.navigate(Route.CatList) {
                                popUpTo(Route.Auth) { inclusive = true }
                            }
                        }
                    )
                }

                composable<Route.CatList>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<CatListViewModel>()

                    CatListScreenRoot(
                        viewModel = viewModel,
                        onCatClick = { cat ->
                            navController.navigate(
                                Route.CatDetail(cat.id)
                            )
                        }
                    )
                }

                composable<Route.CatDetail>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) {
                }
            }
        }
    }
}