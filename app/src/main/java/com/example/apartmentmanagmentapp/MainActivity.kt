package com.example.apartmentmanagmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
    ) {
        Header()
        DebtCard()
        PendingPayments()
        Properties()
        Spacer(modifier = Modifier.weight(1f))
        BottomNav()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header() {
    TopAppBar(
        title = {
            Text("Konsiyon", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF487974)),
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Notifications, contentDescription = "Bildirim", tint = Color.White)
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Send, contentDescription = "Gönder", tint = Color.White)
            }
        }
    )
}

@Composable
fun DebtCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Toplam Borç", fontSize = 18.sp, color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("₺ 200,00", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0EFFF))
                ) {
                    Text("Öde", color = Color(0xFF4EB89D))
                }
            }
        }
    }
}

@Composable
fun PendingPayments() {
    val payments = listOf(
        Payment("Aidat", "100,00", "30 Aralık 2020 / 14 Ocak 2021"),
        Payment("Asansör Bakımı Nar Yöneti", "100,00", "30 Aralık 2020 / 14 Ocak 2021")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("\uD83C\uDFE6 Bekleyen Ödemeleriniz", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        payments.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(it.title, fontSize = 16.sp)
                        Text("₺${it.amount}", fontSize = 16.sp, color = Color.Red)
                    }
                    Text(it.date, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}

data class Payment(val title: String, val amount: String, val date: String)

@Composable
fun Properties() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("\uD83C\uDFE2 Konutlarım", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { }) {
                Text("Yeni Konut Katılım Talebi", color = Color(0xFF4EB89D))
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("PARKVERDE - B / 2", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("AYŞE EMİR", fontSize = 14.sp, color = Color.Gray)
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))
                ) {
                    Text("Yönetiliyor", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun BottomNav() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(Icons.Default.AccessTime, contentDescription = "Genel", tint = Color(0xFF4EB89D))
            },
            label = {
                Text("Genel", color = Color(0xFF4EB89D), fontSize = 12.sp)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(Icons.Default.Message, contentDescription = "Finansal Hareketler", tint = Color.Gray)
            },
            label = {
                Text("Finansal Hareketler", color = Color.Gray, fontSize = 12.sp)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(Icons.Default.List, contentDescription = "Toplantı Odası", tint = Color.Gray)
            },
            label = {
                Text("Toplantı Odası", color = Color.Gray, fontSize = 12.sp)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(Icons.Default.MoreHoriz, contentDescription = "Diğer", tint = Color.Gray)
            },
            label = {
                Text("Diğer", color = Color.Gray, fontSize = 12.sp)
            }
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
