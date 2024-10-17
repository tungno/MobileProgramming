package com.example.lab41

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

// MainActivity is the entry point of the application, extending ComponentActivity for Compose support.
class MainActivity : ComponentActivity() {
    // Override the onCreate method, which is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view of the activity using Jetpack Compose.
        setContent {  // Instead of using XML layouts.
            // Apply the MaterialTheme to the composable functions inside.
            MaterialTheme {
                // Create a Surface container with a background color and fill the maximum size.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Call the MemoryGame composable function to display the game's UI.
                    MemoryGame()
                }
            }
        }
    }
}

@Composable
fun MemoryGame() {
    // Shuffle images only once and remember it across recompositions
    var images by remember { mutableStateOf(
        // mutableStateOf means => makes a value observable to Compose, so the UI can react to changes in that value.
        listOf(
            R.drawable.ic_image1, R.drawable.ic_image1,
            R.drawable.ic_image2, R.drawable.ic_image2,
            R.drawable.ic_image3, R.drawable.ic_image3,
            R.drawable.ic_image4, R.drawable.ic_image4,
            R.drawable.ic_image5, R.drawable.ic_image5,
            R.drawable.ic_image6, R.drawable.ic_image6,
            R.drawable.ic_image7, R.drawable.ic_image7,
            R.drawable.ic_image8, R.drawable.ic_image8,
        ).shuffled() // use .shuffled() => is a Kotlin standard library function that returns a new list with the elements randomly shuffled.
        // eg. val numbers = listOf(1, 2, 3, 4).shuffled()
        // numbers could be [3, 1, 4, 2] or any other random permutation
    )}

    var flippedIndices by remember { mutableStateOf(listOf<Int>()) } // Indices of currently flipped cards
    var matchedIndices by remember { mutableStateOf(setOf<Int>()) }  // Indices of matched cards
    var cardsToCheck by remember { mutableStateOf(listOf<Int>()) }    // Cards currently being checked for match

    // Handle the matching logic with a side effect
    LaunchedEffect(cardsToCheck) {
        if (cardsToCheck.size == 2) {
            delay(1000) // Small delay to let user see the second card
            if (images[cardsToCheck[0]] == images[cardsToCheck[1]]) {
                matchedIndices = matchedIndices + cardsToCheck // Add matched pair
            }
            flippedIndices = listOf() // Reset flipped state
            cardsToCheck = listOf() // Reset checking state
        }
    }

    // Check if all pairs are matched
    val isGameComplete = matchedIndices.size == images.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isGameComplete) {
            // Show restart button when the game is complete
            Text(text = "Congratulations! You've matched all pairs.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Reset the game state
                    images = images.shuffled() // Reshuffle the cards
                    flippedIndices = listOf() // Reset flipped state
                    matchedIndices = setOf() // Reset matched pairs
                    cardsToCheck = listOf() // Reset cards being checked
                }
            ) {
                Text("Restart")
            }
        } else {
            // Create a 4x4 grid for the memory cards
            for (row in 0 until 4) {
                Row {
                    for (col in 0 until 4) {
                        val index = row * 4 + col
                        MemoryCard(
                            imageRes = if (index in flippedIndices || index in matchedIndices) images[index] else R.drawable.ic_question,
                            isFlipped = index in flippedIndices || index in matchedIndices,
                            onClick = {
                                if (flippedIndices.size < 2 && index !in flippedIndices) {
                                    flippedIndices = flippedIndices + index
                                    cardsToCheck = cardsToCheck + index
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MemoryCard(imageRes: Int, isFlipped: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp)
            .clip(CircleShape)
            .background(if (isFlipped) Color.LightGray else Color.Blue)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
    }
}
