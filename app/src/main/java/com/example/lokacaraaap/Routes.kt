
package com.example.lokacaraaap


sealed class Screen(val route: String) {
    // Autentikasi
    object Login : Screen("login")
    object Register : Screen("register")

    // Utama (Bottom Nav)
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Ticket : Screen("ticket")
    object Profile : Screen("profile")

    // Fitur Lainnya
    object AddEvent : Screen("add_event")
    object Notification : Screen("notification")
    object SavedEvents : Screen("saved_events")
}