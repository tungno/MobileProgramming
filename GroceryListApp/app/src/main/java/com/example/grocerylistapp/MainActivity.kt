package com.example.grocerylistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.grocerylistapp.ui.GroceryListApp
import com.example.grocerylistapp.ui.theme.GroceryListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the UI by setting the content for the activity.
        // The GroceryListAppTheme is applied to ensure consistent styling across the app.
        // GroceryListApp is the main composable function that defines the user interface of the app.
        setContent {
            GroceryListAppTheme {
                GroceryListApp() // Main UI of the grocery list application
            }
        }
    }
}
