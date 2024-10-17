package com.example.temperatureconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temperatureconverterapp.ui.theme.TemperatureConverterAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the app's theme and launch the main screen composable
            TemperatureConverterAppTheme {
                TemperatureConverterScreen()
            }
        }
    }
}

@Composable
fun TemperatureConverterScreen() {
    // State to store the temperature input entered by the user
    var temperatureInput by remember { mutableStateOf("") }

    // State to store the selected unit (Celsius by default)
    var selectedUnit by remember { mutableStateOf("°C") }

    // State to track whether the result screen is being displayed
    var showResultScreen by remember { mutableStateOf(false) }

    // State to handle dropdown expansion
    var expanded by remember { mutableStateOf(false) }

    // List of units available for conversion
    val units = listOf("°C", "°F", "°K")

    // State to store the result of the temperature conversion
    var result by remember { mutableStateOf("") }

    // Animation state for scaling the button on press
    val scale = remember { Animatable(1f) }
    val coroutineScope = rememberCoroutineScope()

    // State to detect button press interaction
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    // Change button color when pressed
    val buttonColor = if (isPressed) Color.Blue else Color.DarkGray

    if (!showResultScreen) {
        // Input screen layout for temperature and unit selection
        Scaffold(
            modifier = Modifier
                .fillMaxSize() // Make the scaffold take up the full screen
                .padding(16.dp), // Add padding around the screen content
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center, // Center content vertically
                    horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
                ) {
                    // Title text explaining the app's purpose
                    Text(
                        text = "Enter a temperature you like to convert and choose the unit it is in. The temperature gets converted to °C, °F, and °K.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp, // Font size for the text
                            fontWeight = FontWeight.Bold, // Bold the text
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center, // Center the text alignment
                        modifier = Modifier.padding(bottom = 56.dp) // Add space below the title
                    )

                    // Row containing the temperature input field and dropdown
                    Row(
                        modifier = Modifier
                            .wrapContentWidth() // Wrap the width based on content
                            .padding(16.dp), // Add padding around the row
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Input field for entering the temperature value
                        Box {
                            BasicTextField(
                                value = temperatureInput, // Bind the input field to the state
                                onValueChange = { temperatureInput = it }, // Update state on text input
                                modifier = Modifier
                                    .width(30.dp) // Fixed width for the input field
                                    .padding(0.dp), // No padding around the input field
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Restrict input to numbers
                                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp), // Style the text input
                                decorationBox = { innerTextField ->
                                    Column {
                                        Box {
                                            // Show placeholder when no input is entered
                                            if (temperatureInput.isEmpty()) {
                                                Text("nr.", color = Color.Gray, modifier = Modifier.align(Alignment.CenterStart))
                                            }
                                            innerTextField() // Display user input
                                        }
                                        Spacer(modifier = Modifier.height(4.dp)) // Add space below input
                                        // Underline below the input field
                                        Box(
                                            modifier = Modifier
                                                .height(1.dp)
                                                .fillMaxWidth() // Full-width underline
                                                .background(Color.Black) // Black color for the underline
                                        )
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp)) // Add space between input and dropdown

                        // Dropdown for unit selection
                        Box(
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Display the currently selected unit
                                Text(selectedUnit, fontSize = 16.sp, modifier = Modifier.align(Alignment.CenterVertically))
                                // Button to expand the dropdown menu
                                IconButton(onClick = { expanded = true }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.dropdown),
                                        contentDescription = "Dropdown",
                                        modifier = Modifier.size(16.dp) // Set size of the icon
                                    )
                                }
                            }

                            // Dropdown menu with available units
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }, // Collapse dropdown when dismissed
                                modifier = Modifier
                                    .background(Color.White) // White background for dropdown
                                    .border(1.dp, Color.Gray) // Gray border around the dropdown
                            ) {
                                // Show each unit as a menu item
                                units.forEach { unit ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedUnit = unit // Update selected unit
                                            expanded = false // Collapse the dropdown
                                        },
                                        text = {
                                            Text(
                                                text = unit,
                                                color = if (selectedUnit == unit) Color.Black else Color.Gray
                                            )
                                        },
                                        modifier = Modifier
                                            .background(if (selectedUnit == unit) Color.Gray else Color.White)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp)) // Add space between dropdown and button

                    // Button to trigger temperature conversion
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                // Animate button scaling on click
                                scale.animateTo(1.2f, animationSpec = tween(150))
                                scale.animateTo(1f, animationSpec = tween(150))

                                // Perform temperature conversion and display the result screen
                                result = convertTemperature(temperatureInput, selectedUnit)
                                showResultScreen = true
                            }
                        },
                        modifier = Modifier
                            .scale(scale.value) // Apply scaling animation
                            .width(150.dp)
                            .height(40.dp), // Set fixed width and height for the button
                        shape = RoundedCornerShape(50), // Rounded edges for the button
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor, // Button color based on press state
                            contentColor = Color.White // White text color on button
                        ),
                        interactionSource = interactionSource // Detect button interaction
                    ) {
                        Text("Convert", color = Color.White) // Button label text
                    }
                }
            }
        )
    } else {
        // Result screen showing the converted temperature
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Add padding around the screen content
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center, // Center content vertically
                    horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
                ) {
                    // Display the result text
                    Text(
                        text = "Your converted Temperature:",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp) // Add space below the title
                    )

                    // Display the actual result of the conversion
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp)) // Add space below the result

                    // Back button to return to the input screen
                    Button(
                        onClick = { showResultScreen = false }, // Return to the input screen
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp), // Set fixed width and height for the button
                        shape = RoundedCornerShape(50), // Rounded edges for the button
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor, // Button color based on press state
                            contentColor = Color.White // White text color on button
                        ),
                        interactionSource = interactionSource // Detect button interaction
                    ) {
                        Text("Back", color = Color.White) // Button label text
                    }
                }
            }
        )
    }
}

// Function to convert the temperature based on the selected unit
fun convertTemperature(input: String, unit: String): String {
    // Convert user input to a Double for computation
    val temperature = input.toDoubleOrNull() ?: return "Invalid Input" // Handle invalid input

    val celsius: Double
    val fahrenheit: Double
    val kelvin: Double

    // Conversion logic for Celsius, Fahrenheit, and Kelvin
    when (unit) {
        "°C" -> {
            celsius = temperature
            fahrenheit = (temperature * 9 / 5) + 32
            kelvin = temperature + 273.15
        }
        "°F" -> {
            celsius = (temperature - 32) * 5 / 9
            fahrenheit = temperature
            kelvin = celsius + 273.15
        }
        "°K" -> {
            celsius = temperature - 273.15
            fahrenheit = (celsius * 9 / 5) + 32
            kelvin = temperature
        }
        else -> {
            celsius = 0.0
            fahrenheit = 0.0
            kelvin = 0.0
        }
    }

    // Format and return the conversion result
    return """
        ${String.format("%.2f", celsius)}°C
        ${String.format("%.2f", fahrenheit)}°F
        ${String.format("%.2f", kelvin)}°K
    """.trimIndent()
}

// Compose preview function for IDE
@Preview(showBackground = true)
@Composable
fun TemperatureConverterScreenPreview() {
    TemperatureConverterAppTheme {
        TemperatureConverterScreen()
    }
}
