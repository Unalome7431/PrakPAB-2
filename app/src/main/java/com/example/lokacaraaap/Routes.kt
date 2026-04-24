package com.example.lokacaraaap

sealed class Screen {
    object Login : Screen()
    object Register : Screen()

    object Home : Screen()
    object Explore : Screen()
    object Ticket : Screen()
    object Profile : Screen()

    object AddEvent : Screen()
    object Notification : Screen()
    object SavedEvents : Screen()

    // MISI: Passing Parameter
    // Membawa ID Event dari halaman List ke halaman Detail
    data class DetailEvent(val eventId: Int) : Screen()
}