package com.example.grocerylistapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.grocerylistapp.R

@Composable
fun ScrollIndicator(listState: LazyListState) {
    // Get the total number of items in the LazyColumn and the number of visible items
    val totalItems = listState.layoutInfo.totalItemsCount
    val visibleItems = listState.layoutInfo.visibleItemsInfo.size
    // Calculate the maximum offset value based on the total number of items and visible items
    val maxOffset = (totalItems - visibleItems).toFloat()

    // Calculate the scroll offset as a percentage of the total scrollable area
    // This determines the position of the scroll indicator within the vertical space
    val scrollOffset = if (maxOffset > 0) {
        listState.firstVisibleItemIndex.toFloat() / maxOffset // Ratio of scrolled items to total scrollable items
    } else {
        0f // Avoid division by zero if there are no items to scroll
    }

    // Box layout to contain the scroll indicator image
    Box(
        modifier = Modifier
            .fillMaxHeight() // Fills the height of the parent container
            .width(16.dp) // Sets a fixed width for the scroll indicator
            .padding(end = 8.dp) // Adds padding to the right of the indicator for spacing
    ) {
        // Image for the scroll indicator
        Image(
            painter = painterResource(id = R.drawable.scrolldown), // Uses a drawable resource as the image
            contentDescription = "Scroll indicator", // Accessibility description for the image
            modifier = Modifier
                .align(Alignment.TopEnd) // Aligns the scroll indicator to the top-right corner
                // Dynamically positions the scroll indicator based on the scrollOffset and maxOffset
                .offset(y = (scrollOffset * maxOffset * 60).dp)
        )
    }
}
