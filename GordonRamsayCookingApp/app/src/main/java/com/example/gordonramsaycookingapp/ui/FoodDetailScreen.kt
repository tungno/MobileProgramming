package com.example.gordonramsaycookingapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gordonramsaycookingapp.model.FoodRepository

@Composable
fun FoodDetailScreen(foodId: String) {
    val food = FoodRepository.getFoodById(foodId)

    food?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = it.imageResId),
                contentDescription = it.name,
                modifier = Modifier.fillMaxWidth().height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.name, style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.description)
        }
    }
}
