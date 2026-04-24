package com.example.lokacaraaap

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList

val LocalBackStack = staticCompositionLocalOf<SnapshotStateList<Screen>> {
    error("No BackStack provided")
}

@Composable
fun LokacaraApp() {
    var isLoggedIn by remember { mutableStateOf(false) }

    val startDestination = if (isLoggedIn) Screen.Home else Screen.Login
    val backStack = remember { mutableStateListOf<Screen>(startDestination) }

    CompositionLocalProvider(LocalBackStack provides backStack) {
        BackHandler(enabled = backStack.size > 1) {
            backStack.removeLastOrNull()
        }

        NavDisplay(
            isLoggedIn = isLoggedIn,
            onLoginStatusChange = { isLoggedIn = it }
        )
    }
}

@Composable
fun NavDisplay(
    isLoggedIn: Boolean,
    onLoginStatusChange: (Boolean) -> Unit
) {
    val backStack = LocalBackStack.current
    val currentScreen = backStack.lastOrNull()

    when (currentScreen) {
        is Screen.Login -> {
            LoginScreen(
                onNavigateToHome = {
                    onLoginStatusChange(true)
                    backStack.clear()
                    backStack.add(Screen.Home)
                },
                onNavigateToRegister = { backStack.add(Screen.Register) }
            )
        }
        is Screen.Register -> {
            RegisterScreen(
                onNavigateToLogin = { backStack.removeLastOrNull() }
            )
        }
        is Screen.Home -> {
            HomeScreen(
                onNavigateToExplore = { backStack.add(Screen.Explore) },
                onNavigateToAdd = { backStack.add(Screen.AddEvent) },
                onNavigateToTicket = { backStack.add(Screen.Ticket) },
                onNavigateToProfile = { backStack.add(Screen.Profile) },
                onNavigateToNotification = { backStack.add(Screen.Notification) },
                onNavigateToSaved = { backStack.add(Screen.SavedEvents) }
            )
        }
        is Screen.Explore -> {
            EksploreScreen(
                onNavigateToHome = {
                    backStack.clear()
                    backStack.add(Screen.Home)
                },
                onNavigateToAdd = { backStack.add(Screen.AddEvent) },
                onNavigateToTicket = { backStack.add(Screen.Ticket) },
                onNavigateToProfile = { backStack.add(Screen.Profile) }
            )
        }
        is Screen.Ticket -> {
            TicketScreen(
                onNavigateToHome = { backStack.add(Screen.Home) },
                onNavigateToExplore = { backStack.add(Screen.Explore) },
                onNavigateToAdd = { backStack.add(Screen.AddEvent) },
                onNavigateToProfile = { backStack.add(Screen.Profile) }
            )
        }
        is Screen.Profile -> {
            if (!isLoggedIn) {
                LaunchedEffect(Unit) {
                    backStack.add(Screen.Login)
                }
            } else {
                ProfileScreen(
                    onNavigateToHome = {
                        backStack.clear()
                        backStack.add(Screen.Home)
                    },
                    onNavigateToExplore = { backStack.add(Screen.Explore) },
                    onNavigateToAdd = { backStack.add(Screen.AddEvent) },
                    onNavigateToTicket = { backStack.add(Screen.Ticket) }
                )
            }
        }
        is Screen.AddEvent -> {
            CreateEventScreen(
                onNavigateBack = { backStack.removeLastOrNull() }
            )
        }
        is Screen.Notification -> {
            NotificationScreen(
                onNavigateBack = { backStack.removeLastOrNull() }
            )
        }
        is Screen.SavedEvents -> {
            SavedEventsScreen(
                onNavigateBack = { backStack.removeLastOrNull() }
            )
        }
        is Screen.DetailEvent -> {
        }
        null -> {}
    }
}