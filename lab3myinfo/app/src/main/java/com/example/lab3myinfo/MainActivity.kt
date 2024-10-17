package com.example.lab3myinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab3myinfo.ui.theme.Lab3myinfoTheme
import android.util.Log


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab3myinfoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { padding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            GreetingText()
                            Spacer(modifier = Modifier.height(20.dp))
                            AddButton()  // Button that displays the form
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GreetingText() {
    Text(
        text = "Good afternoon\nThis is my first lab on Android Studio using Kotlin\nPlease click the 'Add' button to add your information",
        style = TextStyle(
            color = Color.Black,
            fontSize = 18.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )
}

@Composable
fun AddButton() {
    var showForm by remember { mutableStateOf(false) }

    // Add Button that toggles form visibility
    Button(
        onClick = {
            showForm = !showForm  // Toggle form visibility
            Log.d("DEBUG_TAG", "Button clicked. Form visible: $showForm")  // Logcat message
            println("Button clicked. Form visible: $showForm")
        },
        modifier = Modifier
            .padding(16.dp)
            .shadow(8.dp, shape = MaterialTheme.shapes.medium),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
    ) {
        Text(text = "Add", color = Color.White)
    }

    if (showForm) {
        UserForm()  // Display form when `showForm` is true
    }
}

@Composable
fun UserForm() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Male") }
    var selectedProgram by remember { mutableStateOf("BDIGSEC") }

    val genders = listOf("Male", "Female")
    val programs = listOf("BDIGSEC", "BPROG", "BIDATA", "Design", "Computer Science/Engineering")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First Name Field
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Last Name Field
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email Field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Gender Radio Buttons
        Text(text = "Gender")
        genders.forEach { gender ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (gender == selectedGender),
                    onClick = { selectedGender = gender }
                )
                Text(text = gender)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Program Dropdown
        Text(text = "Select Program")
        DropdownMenuDemo(programs, selectedProgram) { selectedProgram = it }
    }
}

@Composable
fun DropdownMenuDemo(options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextButton(onClick = { expanded = true }) {
            Text(text = selectedOption)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab3myinfoTheme {
        AddButton()
    }
}
