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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class AddEventActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CreateEventScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen() {
    Scaffold(
        containerColor = FormBgColor,
        topBar = {
            TopAppBar(
                title = { Text("Buat Event Baru", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Close/Back */ }) {
                        Icon(Icons.Default.Close, contentDescription = "Tutup")
                    }
                },
                actions = {
                    Text(
                        text = "Simpan Draf",
                        color = BluePrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FormBgColor)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FormBgColor)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .navigationBarsPadding()
            ) {
                Button(
                    onClick = { /* TODO: Publish Action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Create Your Event", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            SectionUploadPoster()

            CustomInputSection(title = "Nama Event", placeholder = "Contoh: Lokakarya Seni Digital")

            CustomInputSection(
                title = "Kategori",
                placeholder = "Pilih Kategori",
                trailingIcon = Icons.Default.KeyboardArrowDown
            )

            CustomInputSection(title = "Event Organizer", placeholder = "Nama Penyelenggara")

            SectionDateTime()

            SectionLocation()

            SectionDescription()

            SectionQuota()

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
}

@Composable
fun SectionUploadPoster() {
    Column {
        SectionTitle("Poster Event")
        Spacer(modifier = Modifier.height(8.dp))

        val stroke = Stroke(width = 4f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .drawBehind {
                    drawRoundRect(color = Color.LightGray, style = stroke, cornerRadius = CornerRadius(40f))
                }
                .clickable { /* TODO: Open Image Picker */ },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(32.dp), tint = TextSecondary)
                    Icon(Icons.Default.AddCircle, contentDescription = null, modifier = Modifier.size(16.dp), tint = TextSecondary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Unggah Poster (16:9)", color = TextSecondary, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ukuran maksimal 5MB. Format JPG, PNG.", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    }
}

@Composable
fun CustomInputSection(title: String, placeholder: String, trailingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    Column {
        SectionTitle(title)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
            trailingIcon = if (trailingIcon != null) { { Icon(trailingIcon, null, tint = TextSecondary) } } else null,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBgColor,
                unfocusedContainerColor = TextFieldBgColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SectionDateTime() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = CardLightBlueBg,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.CalendarToday, contentDescription = null, tint = BluePrimary, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Waktu & Tanggal", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text("MULAI", fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = "mm/dd/yyyy, --:-- --",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { Icon(Icons.Outlined.CalendarToday, null) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("SELESAI", fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = "mm/dd/yyyy, --:-- --",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { Icon(Icons.Outlined.CalendarToday, null) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun SectionLocation() {
    var isOffline by remember { mutableStateOf(true) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionTitle("Lokasi Event")

            Row(
                modifier = Modifier
                    .background(TextFieldBgColor, RoundedCornerShape(50))
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isOffline) BluePrimary else Color.Transparent)
                        .clickable { isOffline = true }
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text("Offline", color = if (isOffline) Color.White else TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (!isOffline) BluePrimary else Color.Transparent)
                        .clickable { isOffline = false }
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text("Online", color = if (!isOffline) Color.White else TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("masukkan alamat atau link meeting", color = Color.Gray, fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Outlined.LocationOn, null, tint = TextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBgColor,
                unfocusedContainerColor = TextFieldBgColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SectionDescription() {
    Column {
        SectionTitle("Deskripsi")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Detail Event", color = Color.Gray, fontSize = 14.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBgColor,
                unfocusedContainerColor = TextFieldBgColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SectionQuota() {
    var quota by remember { mutableStateOf(50) }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = CardLightBlueBg,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Kuota Peserta", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("Batas maksimal\npendaftar", fontSize = 12.sp, color = TextSecondary)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(50))
                    .padding(4.dp)
            ) {
                IconButton(
                    onClick = { if (quota > 0) quota-- },
                    modifier = Modifier
                        .size(36.dp)
                        .background(TextFieldBgColor, CircleShape)
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Kurang", tint = Color.Black)
                }

                Text(
                    text = "$quota",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                IconButton(
                    onClick = { quota++ },
                    modifier = Modifier
                        .size(36.dp)
                        .background(BluePrimary, CircleShape)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah", tint = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateEventPreview() {
    MaterialTheme {
        CreateEventScreen()
    }
}