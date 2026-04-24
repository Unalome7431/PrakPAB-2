package com.example.lokacaraaap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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

// 1. Bersihkan Activity dari Intent.
// Navigasi sekarang dikendalikan penuh oleh ComposeApp.kt
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToAdd: () -> Unit = {},
    onNavigateToTicket: () -> Unit = {},
    onNavigateToExplore: () -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text("Lokacara", color = BluePrimary, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                ProfileHeader()
                Spacer(modifier = Modifier.height(24.dp))

                MenuSection {
                    MenuItem(icon = Icons.Outlined.DateRange, title = "Event yang Saya Buat")
                    MenuItem(icon = Icons.Outlined.Star, title = "E-Sertifikat Saya")
                }
                Spacer(modifier = Modifier.height(16.dp))

                MenuSection {
                    MenuItem(icon = Icons.Outlined.Person, title = "Pengaturan Akun")
                    MenuItem(icon = Icons.Outlined.Info, title = "Pusat Bantuan")
                    MenuItem(icon = Icons.Outlined.Info, title = "Tentang Lokacara")
                }
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* Aksi Logout (misal: kembali ke Login) */ }.padding(8.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Keluar", tint = RedDanger)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Keluar", color = RedDanger, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(120.dp))
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                // 2. Meneruskan parameter navigasi ke Bottom Bar
                FloatingBottomNavigationBar(
                    onHomeClick = onNavigateToHome,
                    onExploreClick = onNavigateToExplore,
                    onAddClick = onNavigateToAdd,
                    onTicketClick = onNavigateToTicket,
                    onProfileClick = { /* Tidak melakukan apa-apa karena sudah di halaman Profile */ }
                )
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Box(contentAlignment = Alignment.BottomEnd) {
        Box(
            modifier = Modifier.size(100.dp).clip(CircleShape).background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(60.dp), tint = Color.White)
        }
        Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.White).padding(2.dp)) {
            Box(modifier = Modifier.fillMaxSize().clip(CircleShape).background(BluePrimary), contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.CameraAlt, contentDescription = "Edit Foto", tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text("Lionel Messi", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
        shape = RoundedCornerShape(50)
    ) {
        Text(text = "Edit Profil", modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
fun MenuSection(content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) { content() }
}

@Composable
fun MenuItem(icon: ImageVector, title: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable { },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(36.dp).clip(CircleShape).background(IconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = BluePrimary, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Detail", tint = Color.LightGray)
        }
    }
}

@Composable
fun FloatingBottomNavigationBar(
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onTicketClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = Color.White.copy(alpha = 0.85f),
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHomeClick) { Icon(Icons.Outlined.Home, contentDescription = "Home", tint = Color.LightGray) }
                IconButton(onClick = onExploreClick) { Icon(Icons.Outlined.Explore, contentDescription = "Explore", tint = Color.LightGray) }
                Box(
                    modifier = Modifier.size(48.dp).clip(CircleShape).background(IconBackground).clickable { onAddClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(BluePrimary), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                    }
                }
                IconButton(onClick = onTicketClick) { Icon(Icons.Outlined.ConfirmationNumber, contentDescription = "Tiket", tint = Color.LightGray) }
                IconButton(onClick = onProfileClick) { Icon(Icons.Default.Person, contentDescription = "Profil", tint = BluePrimary) }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}