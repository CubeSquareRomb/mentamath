package com.rombsquare.mentalmath

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rombsquare.mentalmath.screens.GameScreen
import com.rombsquare.mentalmath.screens.MainMenu

@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainMenu(navController)
        }
        composable(
            "game/{difficulty}/{sign}",
            arguments = listOf(
                navArgument("difficulty") { defaultValue = "Easy" },
                navArgument("sign") { defaultValue = "+" }
            )
        ) { backStackEntry ->
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "Easy"
            val sign = backStackEntry.arguments?.getString("sign") ?: "+"
            GameScreen(navController, difficulty, sign)
        }
    }
}
