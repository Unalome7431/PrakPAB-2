package com.example.lokacaraaap

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lokacaraaap.*


@Composable
fun LokacaraApp() {
    // Membuat NavController untuk mengelola backstack
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // --- AUTH SCREENS ---
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() }
            )
        }

        // --- MAIN SCREENS (BOTTOM NAV) ---
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToExplore = { navController.navigate(Screen.Explore.route) },
                onNavigateToAdd = { navController.navigate(Screen.AddEvent.route) },
                onNavigateToTicket = { navController.navigate(Screen.Ticket.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onNavigateToNotification = { navController.navigate(Screen.Notification.route) },
                onNavigateToSaved = { navController.navigate(Screen.SavedEvents.route) }
            )
        }

        composable(Screen.Explore.route) {
            EksploreScreen(
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToAdd = { navController.navigate(Screen.AddEvent.route) },
                onNavigateToTicket = { navController.navigate(Screen.Ticket.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Ticket.route) {
            TicketScreen(
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToExplore = { navController.navigate(Screen.Explore.route) },
                onNavigateToAdd = { navController.navigate(Screen.AddEvent.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToExplore = { navController.navigate(Screen.Explore.route) },
                onNavigateToAdd = { navController.navigate(Screen.AddEvent.route) },
                onNavigateToTicket = { navController.navigate(Screen.Ticket.route) }
            )
        }

        // --- FEATURE SCREENS ---
        composable(Screen.AddEvent.route) {
            CreateEventScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Notification.route) {
            NotificationScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.SavedEvents.route) {
            SavedEventsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}