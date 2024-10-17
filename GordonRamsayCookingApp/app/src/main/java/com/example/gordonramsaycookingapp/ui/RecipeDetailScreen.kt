package com.example.gordonramsaycookingapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gordonramsaycookingapp.model.RecipeDetail
import com.example.gordonramsaycookingapp.network.SpoonacularApi
import org.jsoup.Jsoup


@Composable
fun RecipeDetailScreen(recipeId: Int) {
    var recipeDetail by remember { mutableStateOf<RecipeDetail?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(recipeId) {
        try {
            recipeDetail = SpoonacularApi.getRecipeDetails(recipeId)
            if (recipeDetail == null) {
                errorMessage = "Recipe details not found."
            }
        } catch (e: Exception) {
            errorMessage = "Failed to load recipe details."
            Log.e("RecipeDetailScreen", "Error fetching recipe details", e)
        }
    }

    if (errorMessage != null) {
        Text(text = errorMessage!!)
    } else if (recipeDetail != null) {
        val recipe = recipeDetail!!
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = recipe.title,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = Jsoup.parse(recipe.summary).text())
        }
    } else {
        // Optionally display a loading indicator
        Text(text = "Loading...")
    }
}



@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(recipeId = 2)
}
