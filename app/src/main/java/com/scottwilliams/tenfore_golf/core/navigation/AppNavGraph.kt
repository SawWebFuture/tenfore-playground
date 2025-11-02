package com.scottwilliams.tenfore_golf.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.scottwilliams.tenfore_golf.features.home.GolfItem
import com.scottwilliams.tenfore_golf.features.home.HomeScreen
import com.scottwilliams.tenfore_golf.features.splash.SplashScreen
import com.scottwilliams.tenfore_golf.features.login.LoginRoute

object Routes {
    const val Splash = "splash"
    const val Login = "login"
    const val Home = "home"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable(Routes.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.Login) {
            LoginRoute(
                onSuccess = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Login) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.Home) {
            val items = remember {
                listOf(
                    GolfItem("1", "Pro V1 Golf Balls", "Tour-level performance, 12 pack", 54.99),
                    GolfItem("2", "Blade Putter", "Classic feel and control", 179.00),
                    GolfItem("3", "Stand Bag", "Lightweight, 5-way top, dual strap", 139.95)
                )
            }
            HomeScreen(
                items = items,
                onBuy = { item ->
                    // TODO: add to cart, navigate to checkout, or show snackbar
                    // e.g., navController.navigate("checkout/${item.id}")
                }
            )
        }
    }
}