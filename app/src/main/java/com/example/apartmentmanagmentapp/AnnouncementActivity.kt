package com.example.apartmentmanagmentapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnnouncementCard(title: String, content: String, date: String, isNew: Boolean) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isNew) {
                Text(
                    text = "New",
                    color = Color.Green,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(0xFFE8F5E9), CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = content, fontSize = 14.sp, color = Color.Gray, maxLines = 2)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Date", tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = date, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun BottomNavBar() {
    val items = listOf(
        "Home" to Icons.Filled.Home,
        "Calendar" to Icons.Filled.DateRange,
        "Bell" to Icons.Filled.Notifications,
        "User" to Icons.Filled.Person,
        "Settings" to Icons.Filled.Settings
    )

    NavigationBar {
        items.forEach { (title, icon) ->
            NavigationBarItem(
                icon = { Icon(imageVector = icon, contentDescription = title) },
                label = { Text(title) },
                selected = false,
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        TopAppBar(
            title = { Text("Announcements", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            AnnouncementCard("System Maintenance Notice", "Our system will undergo scheduled maintenance.", "Dec 15, 2023", true)
            AnnouncementCard("New Feature Release", "Check out the new dashboard interface!", "Dec 14, 2023", false)
        }
        BottomNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
