
package com.example.gordonramsaycookingapp.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gordonramsaycookingapp.model.SpoonacularRecipe
import com.example.gordonramsaycookingapp.network.SpoonacularApi
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onRecipeClick: (Int) -> Unit) {
    var query by remember { mutableStateOf("") }
    var recipes by remember { mutableStateOf(emptyList<SpoonacularRecipe>()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) } // For error handling
    val coroutineScope = rememberCoroutineScope()
    val shape: Shape = RoundedCornerShape(8.dp)

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    errorMessage = null // Reset error message when query changes
                },
                placeholder = { Text("Search Recipes") },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(shape),
                shape = shape,
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        errorMessage = null
                        try {
                            recipes = SpoonacularApi.searchRecipes(query)
                        } catch (e: Exception) {
                            errorMessage = "Error fetching recipes."
                            Log.e("SearchScreen", "Error fetching recipes", e)
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier
                    .height(56.dp)
                    .clip(shape),
                shape = shape,
                enabled = query.isNotBlank() && !isLoading // Disable when query is empty or loading
            ) {
                Text("Search")
            }
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recipes) { recipe ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRecipeClick(recipe.id) }
                        .padding(8.dp)
                ) {
                    AsyncImage(
                        model = recipe.image,
                        contentDescription = recipe.title,
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(onRecipeClick = {})
}
