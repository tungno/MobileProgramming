package com.example.gordonramsaycookingapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gordonramsaycookingapp.model.Food
import com.example.gordonramsaycookingapp.model.FoodRepository

@Composable
fun FoodListScreen(onFoodClick: (String) -> Unit) {
    val foodList = FoodRepository.getFoodList()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(foodList) { food ->
            FoodListItem(food = food, onClick = { onFoodClick(food.id) })
        }
    }
}

@Composable
fun FoodListItem(food: Food, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(8.dp)) {
        Image(
            painter = painterResource(id = food.imageResId),
            contentDescription = food.name,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = food.name)
    }
}
