package com.example.swit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swit.ui.screens.*

// создание одиночных объевтов прочитать еще про sealed
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Profile : Screen("profile")
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Explore.route) { ExploreScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}
