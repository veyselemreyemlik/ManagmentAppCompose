package com.example.apartmentmanagmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apartmentmanagmentapp.ui.theme.ApartmentManagmentAppTheme
import kotlinx.coroutines.delay

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val userProfile = remember {
        mutableStateOf(
            UserProfile(
                email = "john.doe@example.com",
                phone = "+90 (555) 123 4567",
                apartmentName = "Green Valley Residences",
                apartmentNumber = "B-304"
            )
        )
    }

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)  // Animasyonun gecikmeli başlaması için
        isVisible = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profil Resmi (İlk harf daire içinde)
            AnimatedVisibility(visible = isVisible, enter = androidx.compose.animation.fadeIn(tween(600))) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF34D399)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = userProfile.value.email.first().uppercase(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Profil Bilgileri Kartı
            AnimatedVisibility(visible = isVisible, enter = androidx.compose.animation.fadeIn(tween(600))) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ProfileItem("Email", userProfile.value.email)
                        ProfileItem("Phone Number", userProfile.value.phone)
                        ProfileItem("Apartment Name", userProfile.value.apartmentName)
                        ProfileItem("Apartment Number", userProfile.value.apartmentNumber)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Güncelleme Butonu
            AnimatedVisibility(visible = isVisible, enter = androidx.compose.animation.fadeIn(tween(600))) {
                Button(
                    onClick = { /* Güncelleme işlemi */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF34D399))
                ) {
                    Text("Update Profile", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}

data class UserProfile(
    val email: String,
    val phone: String,
    val apartmentName: String,
    val apartmentNumber: String
)

@Preview(showBackground = true)
@Composable
fun PreviewProfileActivity() {
    ApartmentManagmentAppTheme{
        ProfileScreen()
    }
}