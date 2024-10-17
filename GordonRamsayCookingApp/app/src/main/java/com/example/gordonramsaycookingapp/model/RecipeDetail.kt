
package com.example.gordonramsaycookingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetail(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String
)
