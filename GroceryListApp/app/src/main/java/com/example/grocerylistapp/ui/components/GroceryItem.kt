package com.example.grocerylistapp.ui.components

// Data class representing a single grocery item
data class GroceryItem(
    val name: String,           // The name of the grocery item
    var isChecked: Boolean = false  // Boolean flag indicating whether the item has been checked (marked as completed)
)
