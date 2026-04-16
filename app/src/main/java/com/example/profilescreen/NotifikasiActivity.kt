package com.example.lokacaraapp // Sesuaikan dengan nama package proyekmu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- W A R N A ---
val BackgroundLight = Color(0xFFF8F9FE)
val BluePrimary = Color(0xFF1D5DEB)
val YellowHighlight = Color(0xFFFFB300)
val TextDark = Color(0xFF1A1A1A)
val TextGray = Color(0xFF757575)
val LightBlueIconBg = Color(0xFFF0F4FF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            TopAppBar(
                title = { /* Kosongkan karena judul ada di bawah */ },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Aksi Kembali */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    TextButton(onClick = { /* TODO: Tandai dibaca */ }) {
                        Text(
                            text = "Tandai Semua Dibaca",
                            color = BluePrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundLight)
            )
        },
        floatingActionButton = {
            // Tombol Melayang (FAB) Bantuan
            FloatingActionButton(
                onClick = { /* TODO: Buka Bantuan */ },
                containerColor = BluePrimary,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.QuestionMark, contentDescription = "Bantuan")
            }
        }
    ) { paddingValues ->
        // Menggunakan LazyColumn agar list notifikasi bisa di-scroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- HEADER ---
            item {
                Text(
                    text = "Notifikasi",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Lihat apa yang terjadi di sekitarmu hari ini.",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // --- KARTU HIGHLIGHT (LIVE) ---
            item {
                LiveNotificationCard()
            }

            // --- SEPARATOR JUDUL ---
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "TERBARU",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGray,
                    letterSpacing = 1.sp
                )
            }

            // --- DAFTAR NOTIFIKASI ---
            item {
                NotificationItem(
                    icon = Icons.Default.ConfirmationNumber,
                    title = "Pendaftaran Tech Talk 2024 Berhasil",
                    description = "Tiket elektronik telah dikirim ke email kamu.",
                    time = "2 JAM YANG LALU"
                )
            }

            item {
                NotificationItem(
                    icon = Icons.Default.Bookmark,
                    title = "Pengingat: Seminar Keamanan Siber",
                    description = "Event yang kamu simpan akan diadakan besok pukul 09:00.",
                    time = "5 JAM YANG LALU"
                )
            }

            item {
                NotificationItem(
                    icon = Icons.Default.Campaign,
                    title = "Update Lokasi: Bazaar Kemahasiswaan",
                    description = "Acara dipindahkan ke Lapangan Basket Indoor karena cuaca.",
                    time = "KEMARIN"
                )
            }

            // Spacer bawah agar item terakhir tidak tertutup FAB
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

// Komponen Khusus untuk Kartu Kuning (LIVE)
@Composable
fun LiveNotificationCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = YellowHighlight),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Kotak Ikon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.School, contentDescription = null, tint = TextDark)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Badge "LIVE"
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "LIVE",
                            color = TextDark,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = "Workshop UI/UX Fatisda",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Dimulai dalam 15 menit!",
                    fontSize = 12.sp,
                    color = TextDark.copy(alpha = 0.8f)
                )
            }

            // Ikon Sinyal Transparan di Kanan
            Icon(
                Icons.Default.Sensors,
                contentDescription = null,
                tint = TextDark.copy(alpha = 0.15f),
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

// Komponen Khusus untuk Item Notifikasi Putih
@Composable
fun NotificationItem(icon: ImageVector, title: String, description: String, time: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Dibuat flat
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top // Teks bisa memanjang ke bawah
        ) {
            // Kotak Ikon Biru Muda
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightBlueIconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = BluePrimary, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = TextDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Ikon Jam dan Teks Waktu
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Schedule,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = TextGray.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = time,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextGray.copy(alpha = 0.6f)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Panah Kanan
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Detail",
                tint = TextGray.copy(alpha = 0.5f),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    MaterialTheme {
        NotificationScreen()
    }
}