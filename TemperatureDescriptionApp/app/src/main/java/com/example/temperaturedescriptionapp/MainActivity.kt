package com.example.temperaturedescriptionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.temperaturedescriptionapp.ui.theme.TemperatureDescriptionAppTheme
import com.example.temperaturedescriptionapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureDescriptionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TemperatureApp()
                }
            }
        }
    }
}

@Composable
fun TemperatureApp() {
    var inputText by remember { mutableStateOf("") }
    var temperatureDescription by remember { mutableStateOf("") }
    var iconResId by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TextField for temperature input
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter Temperature (°C)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to trigger the temperature description
        Button(onClick = {
            val temp = inputText.toIntOrNull() ?: 0 // Handle null input safely
            temperatureDescription = temperature(temp)
            iconResId = getIconForTemperature(temp)
        }) {
            Text("Get Description")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the temperature description
        Text(text = temperatureDescription, style = MaterialTheme.typography.headlineMedium, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // Optionally display an icon matching the description
        if (iconResId != 0) {
            Image(painter = painterResource(id = iconResId), contentDescription = null, modifier = Modifier.size(100.dp))
        }
    }
}

// Function to determine temperature description
fun temperature(celsius: Int): String {
    return when {
        celsius >= 25 -> "Hot"
        celsius in 15..24 -> "Warm"
        celsius in 5..14 -> "Cool"
        celsius in -5..4 -> "Cold"
        else -> "Freezing"
    }
}

// Function to return an icon resource based on temperature description
fun getIconForTemperature(celsius: Int): Int {
    return when {
        celsius >= 25 -> R.drawable.hot
        celsius in 15..24 -> R.drawable.warm
        celsius in 5..14 -> R.drawable.cool
        celsius in -5..4 -> R.drawable.cold
        else -> R.drawable.freezing
    }
}
