package com.scottwilliams.tenfore_golf

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scottwilliams.tenfore_golf.core.navigation.AppNavGraph
import com.scottwilliams.tenfore_golf.ui.theme.TenforePlaygroundTheme

@Composable
fun App() {
    TenforePlaygroundTheme {
        val navController = rememberNavController()
        AppNavGraph(navController)
    }
}