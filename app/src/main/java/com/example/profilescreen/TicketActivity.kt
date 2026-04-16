package com.example.profilescreen // Sesuaikan dengan package-mu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TicketActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                TicketScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen() {
    // State untuk ngatur Tab mana yang aktif (0 = Aktif, 1 = Riwayat)
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = BackgroundColor, // Pastikan variabel warna ini ada di ProfileActivity.kt
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
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Outlined.Search, contentDescription = "Cari Tiket", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        }
    ) { innerPadding ->

        // BOX UTAMA (Jurus Overlapping Navbar)
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Custom Tab (Aktif vs Riwayat)
                TicketTabs(
                    selectedIndex = selectedTabIndex,
                    onTabSelected = { selectedTabIndex = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // List Tiket yang bisa di-scroll
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    val tickets = if (selectedTabIndex == 0) getActiveTickets() else getHistoryTickets()

                    items(tickets) { ticket ->
                        TicketCard(ticket = ticket, isActive = selectedTabIndex == 0)
                    }

                    // Spacer gajah biar list paling bawah gak ketutup navbar
                    item { Spacer(modifier = Modifier.height(120.dp)) }
                }
            }

            // NAVBAR MELAYANG TRANSPARAN
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                FloatingTicketBottomNavigationBar()
            }
        }
    }
}

// ==========================================
// KOMPONEN UI
// ==========================================

@Composable
fun TicketTabs(selectedIndex: Int, onTabSelected: (Int) -> Unit) {
    // Background Tab warna abu-abu terang
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color(0xFFE8EAF6), RoundedCornerShape(50))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tab "Aktif"
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(50))
                .background(if (selectedIndex == 0) Color.White else Color.Transparent)
                .clickable { onTabSelected(0) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Aktif",
                color = if (selectedIndex == 0) BluePrimary else Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        // Tab "Riwayat"
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(50))
                .background(if (selectedIndex == 1) Color.White else Color.Transparent)
                .clickable { onTabSelected(1) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Riwayat",
                color = if (selectedIndex == 1) BluePrimary else Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun TicketCard(ticket: TicketData, isActive: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Buka Detail Tiket / QR Code */ },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // BAGIAN ATAS: Info Event
            Row(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = ticket.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = ticket.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.CalendarToday, contentDescription = null, modifier = Modifier.size(14.dp), tint = TextGray)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = ticket.date, fontSize = 12.sp, color = TextGray)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp), tint = TextGray)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = ticket.location, fontSize = 12.sp, color = TextGray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }

            // GARIS PUTUS-PUTUS (Dashed Line style Tiket)
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 16.dp)
            ) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f),
                    strokeWidth = 3f
                )
            }

            // BAGIAN BAWAH: Status & Aksi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Order ID", fontSize = 10.sp, color = Color.Gray)
                    Text(text = ticket.orderId, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }

                if (isActive) {
                    // Tombol E-Tiket warna biru
                    Surface(
                        color = BluePrimary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.clickable { }
                    ) {
                        Text(
                            text = "Lihat E-Tiket",
                            color = BluePrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                } else {
                    // Badge Selesai warna abu-abu
                    Text(
                        text = "Selesai",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }
    }
}

// NAVBAR KHUSUS TIKET (Ikon Tiket Aktif/Biru)
@Composable
fun FloatingTicketBottomNavigationBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding() // JURUS ANTI TENGGELAM
            .padding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = Color.White.copy(alpha = 0.85f), // Efek nembus pandang
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) { Icon(Icons.Outlined.Home, contentDescription = "Home", tint = Color.LightGray) }
                IconButton(onClick = { }) { Icon(Icons.Outlined.Explore, contentDescription = "Explore", tint = Color.LightGray) }

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

                // Active menu (Tiket) - Warna Biru (Pakai Icon Default/Filled)
                IconButton(onClick = { }) { Icon(Icons.Default.ConfirmationNumber, contentDescription = "Tiket", tint = BluePrimary) }

                IconButton(onClick = { }) { Icon(Icons.Outlined.Person, contentDescription = "Profil", tint = Color.LightGray) }
            }
        }
    }
}

// ==========================================
// DATA MODEL & DUMMY DATA
// ==========================================

data class TicketData(
    val title: String,
    val date: String,
    val location: String,
    val orderId: String,
    val imageRes: Int
)

fun getActiveTickets(): List<TicketData> {
    return listOf(
        TicketData(
            title = "Global Innovators Summit 2024",
            date = "24 Okt 2024 • 09:00 WIB",
            location = "Main Hall, Surakarta",
            orderId = "LKC-99281X",
            imageRes = R.drawable.event // Ganti dengan gambar aslimu
        ),
        TicketData(
            title = "Indie Night Session",
            date = "20 Okt 2024 • 19:00 WIB",
            location = "Student Lounge UNS",
            orderId = "LKC-88122Y",
            imageRes = R.drawable.band // Ganti dengan gambar aslimu
        )
    )
}

fun getHistoryTickets(): List<TicketData> {
    return listOf(
        TicketData(
            title = "Workshop UI/UX Fatisda",
            date = "15 Sep 2024 • 10:00 WIB",
            location = "Lab Komputer Terpadu",
            orderId = "LKC-11003Z",
            imageRes = R.drawable.event // Ganti dengan gambar aslimu
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TicketScreenPreview() {
    MaterialTheme {
        TicketScreen()
    }
}