package com.example.levelmeasurementapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Bubble(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = Color.Green,
            radius = size.minDimension / 2
        )
    }
}
