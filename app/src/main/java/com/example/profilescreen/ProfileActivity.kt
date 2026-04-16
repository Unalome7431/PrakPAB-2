package com.example.profilescreen

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

// Warna kustom berdasarkan desain
val BluePrimary = Color(0xFF1D5DEB)
val BackgroundColor = Color(0xFFF8F9FE)
val TextGray = Color(0xFF757575)
val RedDanger = Color(0xFFD32F2F)
val IconBackground = Color(0xFFE8EEFD)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor
                )
            )
        },
        bottomBar = {
            FloatingBottomNavigationBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // -- Bagian Profil --
            ProfileHeader()

            Spacer(modifier = Modifier.height(24.dp))

            // -- Bagian Menu (Grup 1) --
            MenuSection {
                MenuItem(
                    icon = Icons.Outlined.DateRange,
                    title = "Event yang Saya Buat"
                )
                MenuItem(
                    icon = Icons.Outlined.Star, // Representasi E-Sertifikat
                    title = "E-Sertifikat Saya"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // -- Bagian Menu (Grup 2) --
            MenuSection {
                MenuItem(
                    icon = Icons.Outlined.Person,
                    title = "Pengaturan Akun"
                )
                MenuItem(
                    icon = Icons.Outlined.Info, // Representasi Pusat Bantuan
                    title = "Pusat Bantuan"
                )
                MenuItem(
                    icon = Icons.Outlined.Info,
                    title = "Tentang Lokacara"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // -- Tombol Keluar --
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { /* TODO: Handle logout */ }
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Keluar",
                    tint = RedDanger
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Keluar",
                    color = RedDanger,
                    fontWeight = FontWeight.Bold
                )
            }

            // Spacer bawah agar tidak tertutup bottom nav bar
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun ProfileHeader() {
    Box(contentAlignment = Alignment.BottomEnd) {
        // Placeholder untuk gambar profil
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = Color.White
            )
        }

        // Ikon Kamera Overlay
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(2.dp) // Border effect
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(BluePrimary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.CameraAlt,
                    contentDescription = "Edit Foto",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Daffa Arrivo",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(4.dp))



    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = { /* TODO: Handle Edit Profile */ },
        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
        shape = RoundedCornerShape(50) // Pill shape
    ) {
        Text(text = "Edit Profil", modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
fun MenuSection(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        content()
    }
}

@Composable
fun MenuItem(icon: ImageVector, title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { /* TODO: Handle click */ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ikon dengan background bulat
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(IconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Detail",
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun FloatingBottomNavigationBar() {
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
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color.LightGray
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Outlined.Explore,
                        contentDescription = "Explore",
                        tint = Color.LightGray
                    )
                }

                // Tombol Add (Tengah)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(IconBackground)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.size(32.dp).clip(CircleShape).background(BluePrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                    }
                }

                IconButton(onClick = { }) {
                    Icon(
                        Icons.Outlined.ConfirmationNumber,
                        contentDescription = "Tiket",
                        tint = Color.LightGray
                    )
                }
                // Active menu (User)
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profil",
                        tint = BluePrimary
                    )
                }
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