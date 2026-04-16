package com.example.profilescreen // Tetap pakai package-mu!

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

// Warna kustom
val YellowWarning = Color(0xFFFFB300)

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text("Lokacara", color = BluePrimary, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                },
                actions = {
                    IconButton(onClick = { }) { Icon(Icons.Outlined.BookmarkBorder, null, tint = Color.Black) }
                    IconButton(onClick = { }) { Icon(Icons.Outlined.Notifications, null, tint = Color.Black) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        },
        bottomBar = { FloatingBottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // Biar seluruh layar bisa di-scroll ke bawah
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            CategorySection()

            Spacer(modifier = Modifier.height(24.dp))

            // --- SECTION 1: Pilihan Hari Ini ---
            Text(
                text = "Pilihan Hari Ini",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            FeaturedEventCard() // Desain baru sesuai gambar

            Spacer(modifier = Modifier.height(32.dp))

            // --- SECTION 2: Event Terdekat ---
            NearbyEventsSection() // Section baru yang kelupaan!

            Spacer(modifier = Modifier.height(100.dp)) // Jarak ekstra biar gak ketutup bottom bar
        }
    }
}

// =======================================================
// KOMPONEN-KOMPONEN UI
// =======================================================

@Composable
fun CategorySection() {
    val categories = listOf("Semua", "Tech", "Musik", "Seni")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            val isSelected = category == "Semua"
            Surface(
                shape = RoundedCornerShape(50),
                color = if (isSelected) YellowWarning else Color(0xFFEBEBEB),
                modifier = Modifier.clickable { }
            ) {
                Text(
                    text = category,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = if (isSelected) Color.DarkGray else Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// DESAIN BARU: Card Besar dengan teks di dalam gambar
@Composable
fun FeaturedEventCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                // TODO: Aksi kalau Card Event Gede dipencet
                println("Card Global Summit dipencet!")
            }
    ) {
        // --- INI KODE BUAT NAMPILIN GAMBARNYA ---
        // Ganti 'poster_event' dengan nama file gambar yang kamu paste di folder drawable!
        Image(
            painter = painterResource(id = R.drawable.event),
            contentDescription = "Background Event",
            contentScale = ContentScale.Crop, // Biar gambarnya proporsional memenuhi kotak
            modifier = Modifier.fillMaxSize()
        )

        // Efek Gradien Gelap (Biar teks putih tetap keliatan walau gambarnya terang)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)), startY = 150f))
        )

        // Label FEATURED
        Surface(modifier = Modifier.align(Alignment.TopStart).padding(16.dp), color = YellowWarning, shape = RoundedCornerShape(50)) {
            Text("FEATURED", modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }

        // Teks Bawah
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
            Text("Global Innovators\nSummit 2024", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DateRange, null, tint = Color.White, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Oct 24", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.LocationOn, null, tint = Color.White, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Main Hall", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}
// DESAIN BARU: Section Event Terdekat + List Horizontal
@Composable
fun NearbyEventsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Event Terdekat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Acara seru di sekitar kampusmu", color = Color.Gray, fontSize = 14.sp)
            }
            // TAMBAH KLIK DI SINI:
            Text(
                text = "Lihat Semua",
                color = BluePrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // TODO: Aksi kalau tulisan Lihat Semua dipencet
                    println("Tulisan Lihat Semua Dipencet!")
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(2) { NearbyEventCard() }
        }
    }
}
// DESAIN BARU: Card Kecil dengan tombol Join Event
@Composable
fun NearbyEventCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .width(260.dp)
            .clickable {
                // TODO: Aksi kalau Card Kecil dipencet
                println("Card Indie Night dipencet!")
            }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // --- INI KODE GAMBAR BUAT CARD KECIL ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                // Ganti 'indie_band' dengan nama file gambar keduamu
                Image(
                    painter = painterResource(id = R.drawable.band),
                    contentDescription = "Poster Indie",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Bulatan Tanggal
                Surface(modifier = Modifier.align(Alignment.TopEnd).padding(8.dp), shape = CircleShape, color = Color.White) {
                    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("20", color = BluePrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("OCT", color = Color.Gray, fontSize = 10.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Indie Night Session", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Student Lounge • 1.2km", color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Button(
                    onClick = { /* Bisa ditaruh aksi khusus tombol ini juga */ },
                    modifier = Modifier.width(180.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Join Event", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FE)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}