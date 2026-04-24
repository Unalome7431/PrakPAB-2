package com.example.lokacaraaap

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EksploreScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToAdd: () -> Unit = {},
    onNavigateToTicket: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToDetail: (Int) -> Unit = {}
) {
    val events = getDummyEvents()

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Eksplorasi", color = BluePrimary, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp) },
                actions = {
                    IconButton(onClick = { }) { Icon(Icons.Default.Notifications, null, tint = BluePrimary) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item { CustomSearchBar() }
                item { TrendingSection() }
                item { Text("Terbaru di Sekitarmu", fontWeight = FontWeight.Bold, fontSize = 16.sp) }

                itemsIndexed(events) { index, event ->
                    EventCard(event = event, onClick = { onNavigateToDetail(index) })
                }

                item { Spacer(modifier = Modifier.height(120.dp)) }
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                FloatingEksploreBottomNavigationBar(
                    onHomeClick = onNavigateToHome,
                    onExploreClick = {},
                    onAddClick = onNavigateToAdd,
                    onTicketClick = onNavigateToTicket,
                    onProfileClick = onNavigateToProfile
                )
            }
        }
    }
}

@Composable
fun CustomSearchBar() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text(text = "Cari workshop, seminar,...", color = Color.Gray, fontSize = 14.sp) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari", tint = Color.Gray) },
        trailingIcon = { Icon(Icons.Default.Tune, contentDescription = "Filter", tint = BluePrimary) },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = ChipGray,
            unfocusedContainerColor = ChipGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth().height(56.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingSection() {
    Column {
        Text("Lagi Rame \uD83D\uDD25", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TagChip(text = "#KonserSolo", isActive = true)
            TagChip(text = "#WorkshopAI", isActive = false)
            TagChip(text = "#LombaFatisda", isActive = false)
        }
    }
}

@Composable
fun TagChip(text: String, isActive: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (isActive) YellowTag else ChipGray)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text, color = if (isActive) Color.White else Color.DarkGray, fontSize = 12.sp)
    }
}

@Composable
fun FloatingEksploreBottomNavigationBar(
    onHomeClick: () -> Unit,
    onExploreClick: () -> Unit,
    onAddClick: () -> Unit,
    onTicketClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(18.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = Color.White.copy(alpha = 0.9f),
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth() // <-- Tambahkan ini juga agar aman
        ) {
            Row(
                // PERBAIKAN UTAMA DI SINI: Tambahkan fillMaxWidth()
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHomeClick) { Icon(Icons.Outlined.Home, null, tint = Color.LightGray) }
                IconButton(onClick = onExploreClick) { Icon(Icons.Default.Explore, null, tint = BluePrimary) }

                // Perbaikan style tombol Add agar sama dengan Home
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(IconBackground) // Gunakan variabel warna IconBackground kamu
                        .clickable { onAddClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(BluePrimary), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, null, tint = Color.White)
                    }
                }

                IconButton(onClick = onTicketClick) { Icon(Icons.Outlined.ConfirmationNumber, null, tint = Color.LightGray) }
                IconButton(onClick = onProfileClick) { Icon(Icons.Outlined.Person, null, tint = Color.LightGray) }
            }
        }
    }
}

@Composable
fun EventCard(event: EventData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = event.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = event.date, fontSize = 11.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    color = BadgeYellowBg,
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = event.price,
                        color = BadgeYellowText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EksploreScreenPreview() {
    MaterialTheme {
        EksploreScreen()
    }
}
