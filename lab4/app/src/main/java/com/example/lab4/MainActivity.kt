package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab4.ui.theme.Lab4Theme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                NumberGuessingGame()
            }
        }
    }
}

@Composable
fun NumberGuessingGame() {
    var numberToGuess by remember { mutableStateOf(Random.nextInt(1, 1001)) }
    var numberOfTries by remember { mutableStateOf(0) }
    var guess by remember { mutableStateOf(TextFieldValue("")) }
    var fullFeedback by remember { mutableStateOf("Guess a number between 1 and 1000!") }
    var feedbackToShow by remember { mutableStateOf("") }  // Text shown with typewriter effect
    var tooLowCounter by remember { mutableStateOf(0) }

    // Typewriter effect using LaunchedEffect
    LaunchedEffect(fullFeedback) {
        feedbackToShow = ""  // Reset the visible feedback
        for (i in fullFeedback.indices) {
            feedbackToShow = fullFeedback.substring(0, i + 1)
            delay(50)  // Adjust delay for typing speed (50ms per character)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Animated feedback text with typewriter effect
        Text(
            text = feedbackToShow,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
            modifier = Modifier.padding(0.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Guess input
        TextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Enter your guess") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val userGuess = guess.text.toIntOrNull()
                if (userGuess != null) {
                    numberOfTries++

                    // Handle guess comparison
                    fullFeedback = when {
                        userGuess == numberToGuess -> {
                            "Congratulations! You guessed it in $numberOfTries tries."
                        }
                        userGuess < numberToGuess -> {
                            tooLowCounter++
                            if (tooLowCounter >= 3) {
                                "Your guess is too low! Keep trying!"
                            } else {
                                "Your guess is too low!"
                            }
                        }
                        else -> {
                            tooLowCounter = 0  // Reset the counter if the guess is too high or correct
                            "Your guess is too high!"
                        }
                    }

                    // Reset the game when guessed correctly
                    if (userGuess == numberToGuess) {
                        numberToGuess = Random.nextInt(1, 1001)
                        numberOfTries = 0
                        tooLowCounter = 0  // Reset tooLowCounter
                    }
                } else {
                    fullFeedback = "Please enter a valid number."
                }
            }
        ) {
            Text("Validate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberGuessingGame()
}
