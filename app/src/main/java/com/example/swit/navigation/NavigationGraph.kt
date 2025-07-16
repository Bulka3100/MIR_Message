package com.example.swit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swit.ui.screens.ChatListScreen
import com.example.swit.ui.screens.ProfileScreen
import com.example.swit.ui.screens.LoginScreen
import com.example.swit.ui.screens.UsernameScreen

// Объявляем маршруты (имена экранов)
sealed class Screens(val route: String) {
    object ChatListScreen : Screens("chatList")
    object ProfileScreen : Screens("profile")
    object LoginScreen : Screens("login")
    object UsernameScreen : Screens("username")
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(Screens.LoginScreen.route) {
            // Экран логина, при успешной авторизации переходит на список чатов
            LoginScreen(
                onLoginSuccess = {
                    // вот тут как я понял нужно условие вписать но в целом сильно запутался типо что как куда передовать и делать как будто не понимаю уже что происходит
                    if (User is null) {
                        navController.navigate(Screens.UsernameScreen.route)
                    } else
                        navController.navigate(Screens.ChatListScreen.route) {
                            popUpTo(Screens.LoginScreen.route) { inclusive = true }
                        }
                }
            )
        }
        composable(Screens.UsernameScreen.route) {
            UsernameScreen(
                onUsernameSaved = { navController.navigate(Screens.ChatListScreen.route) }
            )


        }

        composable(Screens.ChatListScreen.route) {
            ChatListScreen() // не забудь скобки — мы вызываем функцию
        }

        composable(Screens.ProfileScreen.route) {
            ProfileScreen() // скобки обязательны
        }
    }
}
