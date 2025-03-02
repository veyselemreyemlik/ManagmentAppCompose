package com.example.apartmentmanagmentapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apartmentmanagmentapp.ui.theme.ApartmentManagmentAppTheme
import com.example.paymentui.PaymentScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
fun SignUpScreen() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var isButtonEnabled by remember { mutableStateOf(false) }

    fun getPasswordStrength(pass: String): Int {
        if (pass.isEmpty()) return 0
        var strength = 0
        if (pass.length >= 8) strength += 25
        if (pass.any { it.isUpperCase() }) strength += 25
        if (pass.any { it.isDigit() }) strength += 25
        if (pass.any { !it.isLetterOrDigit() }) strength += 25
        return strength
    }

    val strength = getPasswordStrength(password)
    isButtonEnabled = strength == 100 && name.isNotEmpty() && phone.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Konsiyon", fontSize = 28.sp, color = Color(0xFF00796B), style = MaterialTheme.typography.headlineMedium)
        Text("Create your account", fontSize = 18.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = min(strength.toFloat() / 100, 1f),
            modifier = Modifier.fillMaxWidth(),
            color = when {
                strength <= 25 -> Color.Red
                strength <= 50 -> Color(0xFFFFA500)
                strength <= 75 -> Color.Yellow
                else -> Color(0xFF00796B)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Password must be at least 8 characters, include an uppercase letter, a number, and a special character.", fontSize = 12.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    // Simulate loading process
                    delay(1000)
                    // Handle successful sign up action
                }
            },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Create Account", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            // Navigate to Login screen
        }) {
            Text("Already have an account? Login", color = Color(0xFF00796B))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    ApartmentManagmentAppTheme{
        SignUpScreen()
    }
}
