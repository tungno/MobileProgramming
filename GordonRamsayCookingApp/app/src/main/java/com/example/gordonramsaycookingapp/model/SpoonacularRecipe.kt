package com.example.gordonramsaycookingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class SpoonacularRecipe(
    val id: Int,
    val title: String,
    val image: String
)

