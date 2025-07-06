package com.example.swit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.swit.navigation.NavigationGraph

//я правильно понял что это функция для отображения всего по сути ? просто тут сделали отдельныц фацл для этой функции да?
@Composable
fun MIRApp() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController)
        }

    }
}