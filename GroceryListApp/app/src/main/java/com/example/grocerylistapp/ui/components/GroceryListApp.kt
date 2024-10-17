package com.example.grocerylistapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grocerylistapp.ui.components.GroceryItem
import com.example.grocerylistapp.ui.components.GroceryItemRow
import com.example.grocerylistapp.ui.components.ScrollIndicator

@Composable
fun GroceryListApp() {
    // State to hold the current text entered by the user in the TextField
    var textFieldState by remember { mutableStateOf("") }

    // List of grocery items, stored as a mutable list
    var groceryItems = remember { mutableStateListOf<GroceryItem>() }

    // Function to add a new grocery item to the list
    // Adds the item only if the text field is not empty
    fun addItem() {
        if (textFieldState.isNotBlank()) {
            groceryItems.add(GroceryItem(name = textFieldState))
            textFieldState = ""
        }
    }

    // Function to delete all items that have been checked (marked as completed)
    fun deleteSelectedItems() {
        groceryItems.removeAll { it.isChecked }
    }

    // Function to clear all items from the grocery list
    fun clearAllItems() {
        groceryItems.clear()
    }

    // State for managing the scroll position of the list
    val listState = rememberLazyListState()

    // Main layout of the grocery list app, wrapped in a Column
    Column(
        modifier = Modifier
            .fillMaxSize() // Fills the entire available screen space
            .background(Color(0xFFF8F9FA)) // Light background color
            .padding(16.dp) // Adds padding around the content
    ) {
        // Title for the app displayed at the top of the screen
        Text(
            text = "Grocery List",
            fontSize = 24.sp, // Larger font size for the title
            color = Color(0xFF212529), // Dark color for the text
            modifier = Modifier.align(Alignment.CenterHorizontally) // Center the title horizontally
        )

        Spacer(modifier = Modifier.height(16.dp)) // Spacer to add vertical space

        // Row for input field and add button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth() // Fills the entire width of the screen
        ) {
            // TextField for the user to enter the name of the grocery item
            TextField(
                value = textFieldState, // Binds the current value of the text field
                onValueChange = { textFieldState = it }, // Updates the state when text is entered
                placeholder = {
                    // Placeholder text shown when the text field is empty
                    Text(
                        text = "Add new item...",
                        style = TextStyle(
                            color = Color(0xFF6C7575).copy(alpha = 0.8667f),
                            fontSize = 14.sp
                        )
                    )
                },
                textStyle = TextStyle(fontSize = 14.sp), // Sets the font size for the text
                modifier = Modifier
                    .weight(1f) // The TextField takes up available space
                    .padding(end = 8.dp) // Padding to the right of the text field
                    .height(50.dp) // Fixed height for the TextField
                    .background(Color.White, shape = RoundedCornerShape(8.dp)) // White background with rounded corners
                    .border(1.dp, Color(0xFFCED4DA), shape = RoundedCornerShape(25.dp)), // Border around the text field
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent // No underline for the text field
                )
            )
            // Button to add a new grocery item
            Button(
                onClick = { addItem() }, // Calls the addItem function when clicked
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF28A745)), // Green background for the button
                modifier = Modifier
                    .height(50.dp) // Same height as the text field
                    .padding(start = 8.dp) // Padding to the left of the button
            ) {
                Text(text = "Add", color = Color.White) // Text inside the button
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Spacer to add vertical space

        // Box containing the grocery list, with a scrollable list of items
        Box(
            modifier = Modifier
                .weight(1f) // Takes up the remaining vertical space
                .fillMaxWidth() // Fills the width of the screen
                .background(Color(0xFFF0F0F0)) // Light gray background
        ) {
            // LazyColumn for displaying a scrollable list of grocery items
            LazyColumn(
                state = listState, // State for managing the scroll position
                modifier = Modifier
                    .fillMaxSize() // Fills the available space
                    .padding(end = 16.dp) // Padding on the right to accommodate scroll indicator
            ) {
                // Loop through the groceryItems list and display each item in a row
                items(groceryItems) { item ->
                    GroceryItemRow(
                        item = item, // Pass the current grocery item
                        onCheckedChange = { checked ->
                            // Updates the checked state of the item
                            val updatedItem = item.copy(isChecked = checked)
                            val index = groceryItems.indexOf(item)
                            groceryItems[index] = updatedItem
                        },
                        onDelete = { groceryItems.remove(item) } // Deletes the item when the delete button is clicked
                    )
                }
            }

            // Scroll indicator displayed when the list is scrollable
            ScrollIndicator(listState)
        }

        Spacer(modifier = Modifier.height(16.dp)) // Spacer to add vertical space

        // Row containing the delete and clear buttons at the bottom of the screen
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly, // Space the buttons evenly
            modifier = Modifier.fillMaxWidth() // Fills the width of the screen
        ) {
            // Button to delete checked (selected) items
            Button(
                onClick = { deleteSelectedItems() }, // Calls deleteSelectedItems when clicked
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC3545)), // Red background for the delete button
                modifier = Modifier.weight(1f).padding(8.dp) // Fills equal space and adds padding
            ) {
                Text(text = "Delete", color = Color.White) // Text inside the delete button
            }
            // Button to clear all items from the list
            Button(
                onClick = { clearAllItems() }, // Calls clearAllItems when clicked
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C757D)), // Gray background for the clear button
                modifier = Modifier.weight(1f).padding(8.dp) // Fills equal space and adds padding
            ) {
                Text(text = "Clear", color = Color.White) // Text inside the clear button
            }
        }
    }
}
