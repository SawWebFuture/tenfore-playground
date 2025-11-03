package com.scottwilliams.tenfore_golf.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.scottwilliams.tenfore_golf.features.home.HomeScreen
import com.scottwilliams.tenfore_golf.features.home.model.GolfItem
import com.scottwilliams.tenfore_golf.features.splash.SplashScreen
import com.scottwilliams.tenfore_golf.features.login.LoginRoute

object Routes {
    const val Splash = "splash"
    const val Login = "login"
    const val Home = "home"
}

private const val DEMO_IMAGE =
    "https://media.istockphoto.com/id/1462918815/photo/golf-clubs-and-golf-balls-on-a-green-lawn-in-a-beautiful-golf-course-with-morning-sunshine.jpg?s=612x612&w=0&k=20&c=miGC_L_Ia5EA_X68cTvmpSmzKEsMsfgqRH96Epg54IM="


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
                    GolfItem("1", "Pro V1 Golf Balls", "Tour-level performance, 12 pack", 54.99, DEMO_IMAGE),
                    GolfItem("2", "Blade Putter", "Classic feel and control", 179.00, "https://www.piquacountryclub.com/images/Went-out-with-some-buddies-to.jpg"),
                    GolfItem("3", "Stand Bag", "Lightweight, 5-way top, dual strap", 139.95, "https://www.foresightsports.com/cdn/shop/articles/male-golf-player-on-professional-golf-course.jpg?v=1676490332&width=2048"),
                    GolfItem("4", "Forged Wedge 56°", "High spin, soft feel", 129.00, DEMO_IMAGE),
                    GolfItem("5", "Distance Driver", "Max ball speed and forgiveness", 349.00, DEMO_IMAGE),
                    GolfItem("6", "Tour Glove (3 pack)", "Premium cabretta leather", 39.99, DEMO_IMAGE)
                )
            }
            HomeScreen(
                items = items,
                onBuy = { /* add to cart */ },
                onLogout = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Home) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}