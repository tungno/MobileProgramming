package com.example.grocerylistapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.grocerylistapp.R

@Composable
fun GroceryItemRow(item: GroceryItem, onCheckedChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    // Layout for a row that represents each grocery item, with a checkbox, item name, and delete button
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth() // Ensure row takes up full width
            .padding(8.dp)  // Adds padding around the row for spacing
    ) {
        // Checkbox to mark the item as checked/unchecked
        // Colors change based on whether the item is checked or not
        Checkbox(
            checked = item.isChecked,  // Sets the checkbox state
            onCheckedChange = { onCheckedChange(it) }, // Calls function to handle checkbox state change
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF28A745), // Green color when checked
                uncheckedColor = Color(0xFFCED4DA) // Gray color when unchecked
            )
        )
        // Displays the item name next to the checkbox
        Text(
            text = item.name, // Shows the grocery item name
            modifier = Modifier.weight(1f) // Gives the Text weight so it fills available space
        )
        // Button to delete the item from the list
        // When clicked, it calls the onDelete function passed to the composable
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete), // Displays delete icon
                contentDescription = "Delete Item",  // Accessibility text for the delete button
                tint = Color(0xFF6C757D) // Sets a grayish color for the delete icon
            )
        }
    }
}
