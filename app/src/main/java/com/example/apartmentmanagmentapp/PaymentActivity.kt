package com.example.paymentui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apartmentmanagmentapp.ui.theme.ApartmentManagmentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApartmentManagmentAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    PaymentScreen()
                }
            }
        }
    }
}

@Composable
fun PaymentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentTitle()
        Spacer(modifier = Modifier.height(16.dp))
        ProgressSteps()
        Spacer(modifier = Modifier.height(16.dp))
        PaymentForm()
        Spacer(modifier = Modifier.height(16.dp))
        PaymentSummary()


    }
}
@Composable
fun PaymentTitle() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Hata düzeltilmiş

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Ödeme Özeti", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

@Composable
fun PaymentForm() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Hata düzeltilmiş

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Kart Numarası", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Son Kullanma Tarihi", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("CVV", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun PaymentSummary() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Hata düzeltilmiş

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Ödeme Özeti", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Toplam Tutar: 100₺", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("Ödeme Yap")
            }
        }
    }
}

@Composable
fun ProgressSteps() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StepItem(label = "Özet", isActive = true, isCompleted = true)
        StepItem(label = "Ödeme", isActive = true, isCompleted = false)
        StepItem(label = "Sonuç", isActive = false, isCompleted = false)
    }
}

@Composable
fun StepItem(label: String, isActive: Boolean, isCompleted: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val animatedAlpha by animateFloatAsState(
            targetValue = if (isActive || isCompleted) 1f else 0.5f,
            animationSpec = tween(durationMillis = 500)
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50))
                .background(if (isCompleted) Color.Green else if (isActive) Color.Blue else Color.Gray)
                .clickable {}.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = if (isCompleted) "✓" else label.first().toString(), color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
     ApartmentManagmentAppTheme{
        PaymentScreen()
    }
}
