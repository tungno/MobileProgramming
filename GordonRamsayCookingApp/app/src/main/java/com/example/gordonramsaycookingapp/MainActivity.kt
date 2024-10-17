package com.example.gordonramsaycookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gordonramsaycookingapp.ui.FoodDetailScreen
import com.example.gordonramsaycookingapp.ui.FoodListScreen
import com.example.gordonramsaycookingapp.ui.HomeScreen
import com.example.gordonramsaycookingapp.ui.RecipeDetailScreen
import com.example.gordonramsaycookingapp.ui.SearchScreen
import com.example.gordonramsaycookingapp.ui.theme.GordonRamsayCookingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GordonRamsayCookingAppTheme {
                val navController = rememberNavController()
                NavigationComponent(navController)
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onClick = { navController.navigate("food_list") },
                onSpoonacularClick = { navController.navigate("search") }
            )
        }
        composable("food_list") {
            FoodListScreen(onFoodClick = { foodId ->
                navController.navigate("food_detail/$foodId")
            })
        }
        composable("food_detail/{foodId}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            foodId?.let {
                FoodDetailScreen(foodId = it)
            }
        }
        composable("search") {
            SearchScreen(onRecipeClick = { recipeId ->
                navController.navigate("recipe_detail/$recipeId")
            })
        }
        composable("recipe_detail/{recipeId}") { backStackEntry ->
            val recipeIdString = backStackEntry.arguments?.getString("recipeId")
            val recipeId = recipeIdString?.toIntOrNull()
            recipeId?.let {
                RecipeDetailScreen(recipeId = it)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GordonRamsayCookingAppTheme {
        HomeScreen(
            onClick = {},
            onSpoonacularClick = {}  // Pass this parameter as well
        )
    }
}
