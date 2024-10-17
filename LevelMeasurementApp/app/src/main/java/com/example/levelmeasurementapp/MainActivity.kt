package com.example.levelmeasurementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.levelmeasurementapp.ui.LevelScreen
import com.example.levelmeasurementapp.ui.LevelViewModel
import com.example.levelmeasurementapp.ui.theme.LevelMeasurementAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: LevelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LevelMeasurementAppTheme {
                LevelScreen(viewModel = viewModel, modifier = Modifier)
            }
        }
    }
}
