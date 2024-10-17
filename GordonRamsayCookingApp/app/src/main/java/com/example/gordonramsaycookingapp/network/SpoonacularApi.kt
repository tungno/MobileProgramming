package com.example.gordonramsaycookingapp.network

import android.util.Log
import com.example.gordonramsaycookingapp.model.RecipeDetail
import com.example.gordonramsaycookingapp.model.SpoonacularRecipe
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object SpoonacularApi {
    private const val BASE_URL = "https://api.spoonacular.com/recipes/"
    private const val API_KEY = "ac6e4b46bfe246658b1e10042e80aa1d"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun searchRecipes(query: String): List<SpoonacularRecipe> {
        return try {
            val response: SpoonacularSearchResponse = client.get {
                url.takeFrom("${BASE_URL}complexSearch")
                parameter("apiKey", API_KEY)
                parameter("query", query)
            }.body()
            response.results
        } catch (e: Exception) {
            Log.e("SpoonacularApi", "Error searching recipes", e)
            emptyList()
        }
    }



    suspend fun getRecipeDetails(recipeId: Int): RecipeDetail? {
        return try {
            client.get {
                url.takeFrom("${BASE_URL}${recipeId}/information")
                parameter("apiKey", API_KEY)
            }.body()
        } catch (e: Exception) {
            Log.e("SpoonacularApi", "Error fetching recipe details", e)
            null
        }
    }



}

@Serializable
data class SpoonacularSearchResponse(
    val results: List<SpoonacularRecipe>
)


