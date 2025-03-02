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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val passwordStrength = getPasswordStrength(password)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Create an Account", fontSize = 24.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Password Strength: $passwordStrength%",
                color = when {
                    passwordStrength == 100 -> Color.Green
                    passwordStrength >= 50 -> Color.Yellow
                    else -> Color.Red
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Checkbox(checked = agreedToTerms, onCheckedChange = { agreedToTerms = it })
                Text("I agree to the Terms of Service and Privacy Policy")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (passwordStrength == 100 && agreedToTerms) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Account Created Successfully")
                        }
                    } else {
                        Toast.makeText(context, "Ensure strong password and agree to terms", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = passwordStrength == 100 && agreedToTerms,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Create Account")
            }
        }
    }
}

fun getPasswordStrength(pass: String): Int {
    if (pass.isEmpty()) return 0
    var strength = 0
    if (pass.length >= 8) strength += 25
    if (pass.any { it.isUpperCase() }) strength += 25
    if (pass.any { it.isDigit() }) strength += 25
    if (pass.any { !it.isLetterOrDigit() }) strength += 25
    return strength
}


@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    ApartmentManagmentAppTheme{
        SignUpScreen()
    }
}
