package com.example.swit.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

data class BottomNavItem(val name: String, val route: String, val icon: ImageVector)

val bottomNavItems = listOf(
    BottomNavItem("Главная", "home", Icons.Default.Home),
    BottomNavItem("Изучить", "explore", Icons.Default.Search),
    BottomNavItem("Профиль", "profile", Icons.Default.Person)
)

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) }
            )
        }
    }
}
