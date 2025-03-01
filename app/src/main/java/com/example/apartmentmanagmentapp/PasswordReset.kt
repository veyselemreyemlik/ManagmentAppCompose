package com.example.apartmentmanagmentapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.LinearProgressIndicator

@Composable
fun PasswordResetScreen() {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    val context = LocalContext.current

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

    if (isSuccess) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Password Reset Successful!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Reset Password", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            PasswordField("New Password", password, showPassword, { password = it }, { showPassword = it })

            LinearProgressIndicator(
                progress = strength / 100f,
                color = when {
                    strength <= 25 -> Color.Red
                    strength <= 50 -> Color.Yellow
                    strength <= 75 -> Color.Blue
                    else -> Color.Green
                },
                modifier = Modifier.fillMaxWidth().height(6.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            PasswordField("Confirm Password", confirmPassword, showConfirmPassword, { confirmPassword = it }, { showConfirmPassword = it })

            if (confirmPassword.isNotEmpty() && password != confirmPassword) {
                Text("Passwords do not match", color = Color.Red, fontSize = 14.sp)
            }

            Button(
                onClick = {
                    if (password == confirmPassword && strength == 100) {
                        isSuccess = true
                        Toast.makeText(context, "Password Reset Successfully", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = password == confirmPassword && strength == 100,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Reset Password")
            }
        }
    }
}

@Composable
fun PasswordField(label: String, value: String, isVisible: Boolean, onValueChange: (String) -> Unit, onToggleVisibility: (Boolean) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { onToggleVisibility(!isVisible) }) {
                Icon(
                    imageVector = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewPasswordResetScreen() {
    PasswordResetScreen()
}
