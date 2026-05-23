package com.example.dattuadulich.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dattuadulich.ui.screen.detail.DetailScreen
import com.example.dattuadulich.ui.screen.explore.ExploreScreen
import com.example.dattuadulich.ui.screen.home.HomeScreen
import com.example.dattuadulich.ui.screen.mybooking.MyBookingScreen
import com.example.dattuadulich.ui.screen.setting.SettingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBarScreen(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Explore.route) { ExploreScreen(navController) }
            composable(Screen.MyBooking.route) { MyBookingScreen() }
            composable(Screen.Setting.route) { SettingScreen() }
            
            composable(Screen.Detail.route) { backStackEntry ->
                val destinationName = backStackEntry.arguments?.getString("destinationName") ?: "Điểm đến"
                DetailScreen(navController, destinationName)
            }
        }
    }
}