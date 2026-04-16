package com.example.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val ChipGray = Color(0xFFEAEAEA)
val YellowTag = Color(0xFFFFB300)
val BadgeYellowBg = Color(0xFFFFF3E0)
val BadgeYellowText = Color(0xFFE65100)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EksploreScreen() {
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lokacara",
                        color = BluePrimary,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = BluePrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifikasi", tint = BluePrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        },
        bottomBar = {
            FloatingEksploreBottomNavigationBar()
        }
    ) { innerPadding ->
        // Menggunakan LazyColumn agar layar bisa di-scroll ke bawah
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            // 1. Bilah Pencarian (Search Bar)
            item {
                CustomSearchBar()
            }

            // 2. Bagian Trending (Lagi Rame)
            item {
                TrendingSection()
            }

            // 3. Header Daftar Acara
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Terbaru di Sekitarmu",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Lihat Semua",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = BluePrimary,
                        modifier = Modifier.clickable { }
                    )
                }
            }

            // 4. Daftar Acara (Event Cards)
            items(getDummyEvents()) { event ->
                EventCard(event)
            }

            item { Spacer(modifier = Modifier.height(100.dp)) } // Spacer hindari tertutup bottom bar
        }
    }
}

@Composable
fun CustomSearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(text = "Cari workshop, seminar,...", color = Color.Gray, fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Cari", tint = Color.Gray)
        },
        trailingIcon = {
            Icon(Icons.Default.Tune, contentDescription = "Filter", tint = BluePrimary)
        },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = ChipGray,
            unfocusedContainerColor = ChipGray,
            disabledContainerColor = ChipGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingSection() {
    Column {
        Text(
            text = "Lagi Rame \uD83D\uDD25", // Emoji api
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))

        // FlowRow otomatis memindahkan elemen ke baris bawah jika tidak muat
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TagChip(text = "#KonserSolo", isActive = true)
            TagChip(text = "#WorkshopAI", isActive = false)
            TagChip(text = "#LombaFatisda", isActive = false)
            TagChip(text = "#SemnasInformatika", isActive = false)
            TagChip(text = "#CampusLife", isActive = false)
        }
    }
}

@Composable
fun TagChip(text: String, isActive: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (isActive) YellowTag else ChipGray)
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isActive) Color.White else Color.DarkGray,
            fontSize = 12.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun EventCard(event: EventData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar Acara
            Image(
                painter = painterResource(id = event.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Detail Acara
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.CalendarToday, contentDescription = null, modifier = Modifier.size(12.dp), tint = TextGray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = event.date, fontSize = 11.sp, color = TextGray)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.LocationOn, contentDescription = null, modifier = Modifier.size(12.dp), tint = TextGray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = event.location, fontSize = 11.sp, color = TextGray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Bagian Harga & Bookmark
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(BadgeYellowBg)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(text = event.price, color = BadgeYellowText, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }

                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = "Simpan",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// Komponen Navigasi Bawah Khusus Eksplore
@Composable
fun FloatingEksploreBottomNavigationBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = Color.White,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.LightGray) }

                // Active menu (Explore) - Warna Biru
                IconButton(onClick = { }) { Icon(Icons.Default.Explore, contentDescription = "Explore", tint = BluePrimary) }

                // Tombol Add (Tengah)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(IconBackground)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(BluePrimary), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                    }
                }

                IconButton(onClick = { }) { Icon(Icons.Outlined.ConfirmationNumber, contentDescription = "Tiket", tint = Color.LightGray) }
                IconButton(onClick = { }) { Icon(Icons.Outlined.Person, contentDescription = "Profil", tint = Color.LightGray) }
            }
        }
    }
}

// Data Model & Dummy Data
data class EventData(
    val title: String,
    val date: String,
    val location: String,
    val price: String,
    val imageRes: Int
)

fun getDummyEvents(): List<EventData> {
    return listOf(
        EventData(
            "Seminar Nasional: Masa Depan AI di Industri Kreatif",
            "24 Okt 2023 • 09:00",
            "Auditorium UNS, Surakarta",
            "Gratis",
            R.drawable.event
        ),
        EventData(
            "Festival Budaya: Harmoni Nusantara",
            "28 Okt 2023 • 15:00",
            "Pamedan Mangkunegaran",
            "Gratis",
            R.drawable.band
        ),
        EventData(
            "Workshop UI/UX: Crafting High-End Portfolios",
            "30 Okt 2023 • 10:00",
            "Creative Space Solo",
            "Gratis",
            R.drawable.event
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EksploreScreenPreview() {
    MaterialTheme {
        EksploreScreen()
    }
}