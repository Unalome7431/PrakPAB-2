package com.example.lokacaraapp // SESUAIKAN DENGAN NAMA PACKAGE KAMU

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- W A R N A ---
val ChipGray = Color(0xFFE8E9F1) // Abu-abu kebiruan untuk chip non-aktif
val ChipActive = Color(0xFFFFB300) // Kuning untuk chip aktif
val IconBgLightBlue = Color(0xFFDEE8FF) // Latar ikon bookmark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedEventsScreen() {
    // State untuk kategori yang sedang dipilih
    var selectedCategory by remember { mutableStateOf("Tech") }

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Saved Events",
                        color = BluePrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Kembali */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = BluePrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Fitur Urutkan */ }) {
                        Icon(Icons.Default.SwapVert, contentDescription = "Urutkan", tint = BluePrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 1. Judul Utama
            item {
                Text(
                    text = "Tersimpan",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 8.dp)                )
            }

            // 2. Baris Filter Kategori (Bisa di-scroll menyamping)
            item {
                val categories = listOf("Semua", "Tech", "Musik", "Seminar", "Olahraga")

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    items(categories) { category ->
                        FilterChipItem(
                            text = category,
                            isActive = category == selectedCategory,
                            onClick = { selectedCategory = category }
                        )
                    }
                }
            }

            // 3. Daftar Acara yang Tersimpan
            val savedEvents = getDummySavedEvents()
            items(savedEvents) { event ->
                SavedEventCard(event = event)
            }
        }
    }
}

// Komponen Khusus untuk Chip Kategori
@Composable
fun FilterChipItem(text: String, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (isActive) ChipActive else ChipGray)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = if (isActive) TextDark else TextGray,
            fontSize = 14.sp,
            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// Komponen Khusus untuk Kartu Acara
@Composable
fun SavedEventCard(event: SavedEventData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable { /* TODO: Ke halaman detail acara */ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Desain flat (tanpa bayangan tebal)
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ikon Bookmark Biru Bulat
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(IconBgLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Tersimpan",
                    tint = BluePrimary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Teks Judul dan Subjudul
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextDark,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.subtitle,
                    fontSize = 12.sp,
                    color = TextGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Panah Kanan
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Detail",
                tint = Color.LightGray
            )
        }
    }
}

// Data Model & Data Buatan (Dummy)
data class SavedEventData(val title: String, val subtitle: String)

fun getDummySavedEvents(): List<SavedEventData> {
    return listOf(
        SavedEventData("Tech Talk 2024 - UNS...", "Tech • Fatisda UNS • May 10"),
        SavedEventData("Innovation Summit I...", "Tech • Auditorium Pusat • June 15"),
        SavedEventData("Cloud Computing W...", "Tech • Lab Komputer A • June 22"),
        SavedEventData("Cyber Security Basics", "Tech • Virtual Event • July 04"),
        SavedEventData("AI & Ethics Roundtable", "Tech • Gd. Pascasarjana • July 12")
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SavedEventsScreenPreview() {
    MaterialTheme {
        SavedEventsScreen()
    }
}