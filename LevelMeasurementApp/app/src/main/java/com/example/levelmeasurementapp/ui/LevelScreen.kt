package com.example.levelmeasurementapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.levelmeasurementapp.ui.components.Bubble

@Composable
fun LevelScreen(viewModel: LevelViewModel, modifier: Modifier = Modifier) {
    val orientation by viewModel.orientation.collectAsState()

    // Scale the orientation values appropriately
    val bubbleX = orientation.roll * 100
    val bubbleY = orientation.pitch * 100

    Box(modifier = modifier.fillMaxSize()) {
        // Position the Bubble composable using offset
        Bubble(
            modifier = Modifier
                .size(50.dp)
                .offset(
                    x = bubbleX.dp,
                    y = bubbleY.dp
                )
                .align(Alignment.Center)
        )

        // Display the roll and pitch values for debugging
        Text(
            text = "Pitch: ${orientation.pitch}, Roll: ${orientation.roll}",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )
    }
}
