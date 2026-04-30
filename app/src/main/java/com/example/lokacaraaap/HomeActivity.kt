package com.example.lokacaraaap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
fun HomeScreen(
    onNavigateToExplore: () -> Unit = {},
    onNavigateToAdd: () -> Unit = {},
    onNavigateToTicket: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToNotification: () -> Unit = {},
    onNavigateToSaved: () -> Unit = {},
    onNavigateToDetail: (Int) -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Lokacara", color = BluePrimary, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp) },
                actions = {
                    IconButton(onClick = onNavigateToSaved) { Icon(Icons.Outlined.BookmarkBorder, null, tint = Color.Black) }
                    IconButton(onClick = onNavigateToNotification) { Icon(Icons.Outlined.Notifications, null, tint = Color.Black) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        },
        bottomBar = {
            FloatingHomeBottomNavigationBar(
                onHomeClick = {},
                onExploreClick = onNavigateToExplore,
                onAddClick = onNavigateToAdd,
                onTicketClick = onNavigateToTicket,
                onProfileClick = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        HomeScreenContent(
            innerPadding = innerPadding,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}

@Composable
private fun HomeScreenContent(
    innerPadding: PaddingValues,
    onNavigateToDetail: (Int) -> Unit
) {
    val events = getDummyEvents()
    val featuredEvent = events.firstOrNull()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()),
        contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 32.dp)
    ) {
        item(key = "category_section") {
            Spacer(modifier = Modifier.height(8.dp))
            CategorySection()
        }

        item(key = "popular_header") {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Event Populer",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (featuredEvent != null) {
            item(key = "featured_event") {
                FeaturedEventCard(event = featuredEvent, onClick = { onNavigateToDetail(0) })
            }
        }

        item(key = "nearby_section") {
            Spacer(modifier = Modifier.height(32.dp))
            NearbyEventsSection(events = events, onEventClick = onNavigateToDetail)
        }
    }
}

@Composable
fun FloatingHomeBottomNavigationBar(
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHomeClick) { Icon(Icons.Default.Home, contentDescription = "Home", tint = BluePrimary) }

                IconButton(onClick = onExploreClick) { Icon(Icons.Default.Explore, contentDescription = "Explore", tint = Color.LightGray) }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(IconBackground)
                        .clickable { onAddClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(BluePrimary), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                    }
                }

                IconButton(onClick = onTicketClick) { Icon(Icons.Outlined.ConfirmationNumber, contentDescription = "Tiket", tint = Color.LightGray) }
                IconButton(onClick = onProfileClick) { Icon(Icons.Outlined.Person, contentDescription = "Profil", tint = Color.LightGray) }
            }
        }
    }
}

@Composable
fun CategorySection() {
    val categories = listOf("Semua", "Tech", "Musik", "Seni")
    var selectedCategory by remember { mutableStateOf("Semua") }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(
            count = categories.size,
            key = { index -> "category_${categories[index]}" }
        ) { index ->
            val category = categories[index]
            val isSelected = category == selectedCategory
            Surface(
                shape = RoundedCornerShape(50),
                color = if (isSelected) YellowWarning else Color(0xFFEBEBEB),
                modifier = Modifier.clickable { 
                    selectedCategory = category 
                }
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

@Composable
fun FeaturedEventCard(event: EventData, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = event.imageRes),
            contentDescription = "Background Event",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)), startY = 150f))
        )
        Surface(modifier = Modifier.align(Alignment.TopStart).padding(16.dp), color = YellowWarning, shape = RoundedCornerShape(50)) {
            Text("FEATURED", modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
            Text(event.title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp, maxLines = 2)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DateRange, null, tint = Color.White, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(event.date, color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.LocationOn, null, tint = Color.White, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(event.location, color = Color.White, fontSize = 12.sp, maxLines = 1)
            }
        }
    }
}

@Composable
fun NearbyEventsSection(events: List<EventData>, onEventClick: (Int) -> Unit) {
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
            Text(
                text = "Lihat Semua",
                color = BluePrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(
                count = events.size,
                key = { index -> "nearby_${events[index].title}_$index" }
            ) { index ->
                NearbyEventCard(event = events[index], onClick = { onEventClick(index) })
            }
        }
    }
}

@Composable
fun NearbyEventCard(event: EventData, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.width(260.dp).clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier.fillMaxWidth().height(140.dp).clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = event.imageRes),
                    contentDescription = "Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(event.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(event.location, color = Color.Gray, fontSize = 12.sp, maxLines = 1)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = onClick,
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