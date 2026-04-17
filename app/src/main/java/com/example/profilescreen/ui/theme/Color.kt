package com.example.profilescreen // Sesuaikan dengan nama package kamu

import androidx.compose.ui.graphics.Color

// ==========================================
// WARNA UTAMA (PRIMARY COLORS)
// ==========================================
val BluePrimary = Color(0xFF1D5DEB)
val RedDanger = Color(0xFFD32F2F)

// Kumpulan warna Kuning yang menggunakan HEX sama (0xFFFFB300)
val YellowPrimary = Color(0xFFFFB300)
val YellowTag = YellowPrimary       // Dipakai di EksploreActivity
val YellowWarning = YellowPrimary   // Dipakai di HomeActivity
val YellowHighlight = YellowPrimary // Dipakai di NotifikasiActivity
val ChipActive = YellowPrimary      // Dipakai di SavedEventActivity

// ==========================================
// WARNA LATAR BELAKANG (BACKGROUNDS)
// ==========================================
// Kumpulan warna background dasar yang menggunakan HEX sama (0xFFF8F9FE)
val BackgroundColor = Color(0xFFF8F9FE) // Dipakai di Home, Profile, Login, Register, Ticket
val FormBgColor = BackgroundColor       // Dipakai di AddEventActivity
val BackgroundLight = BackgroundColor   // Dipakai di NotifikasiActivity & SavedEventActivity

val CardLightBlueBg = Color(0xFFF3F5FC) // Dipakai di AddEventActivity

// ==========================================
// WARNA TEKS (TYPOGRAPHY)
// ==========================================
val TextDark = Color(0xFF1A1A1A)

// Warna abu-abu teks (0xFF757575)
val TextGray = Color(0xFF757575)
val TextSecondary = TextGray // Dipakai di AddEventActivity

// ==========================================
// WARNA ELEMEN UI (BORDER, DIVIDER, TEXTFIELD)
// ==========================================
val TextFieldBgColor = Color(0xFFE8EAF6)

// Garis outline dan divider (0xFFE0E0E0)
val OutlineGray = Color(0xFFE0E0E0) // Dipakai di LoginActivity
val DividerGray = OutlineGray       // Dipakai di RegisterActivity

// ==========================================
// WARNA CHIP / TAG
// ==========================================
val ChipGray = Color(0xFFEAEAEA)       // Dipakai di EksploreActivity
val ChipGrayVariant = Color(0xFFE8E9F1) // Dipakai di SavedEventActivity (Sebelumnya bernama ChipGray)

val BadgeYellowBg = Color(0xFFFFF3E0)   // Dipakai di EksploreActivity
val BadgeYellowText = Color(0xFFE65100) // Dipakai di EksploreActivity

// ==========================================
// WARNA LATAR IKON (ICON BACKGROUNDS)
// ==========================================
val IconBackground = Color(0xFFE8EEFD)    // Dipakai di ProfileActivity
val LightBlueIconBg = Color(0xFFF0F4FF)   // Dipakai di NotifikasiActivity
val IconBgLightBlue = Color(0xFFDEE8FF)   // Dipakai di SavedEventActivity