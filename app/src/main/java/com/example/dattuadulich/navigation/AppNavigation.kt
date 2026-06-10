// điều hướng màn hình
package com.example.dattuadulich.navigation

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dattuadulich.data.local.AppDatabase
import com.example.dattuadulich.repository.BookingRepository
import com.example.dattuadulich.ui.screen.detail.DetailScreen
import com.example.dattuadulich.ui.screen.explore.ExploreScreen
import com.example.dattuadulich.ui.screen.home.HomeScreen
import com.example.dattuadulich.ui.screen.mybooking.MyBookingScreen
import com.example.dattuadulich.ui.screen.setting.SettingScreen
import com.example.dattuadulich.ui.screen.setting.SettingViewModel
import com.example.dattuadulich.ui.screen.booking.BookingScreen
import com.example.dattuadulich.ui.screen.mybooking.MyBookingViewModel
import com.example.dattuadulich.ui.screen.booking.BookingViewModel

@Composable
fun AppNavigation(settingViewModel: SettingViewModel) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background, // Thêm dòng này để chắc chắn
        bottomBar = { BottomBarScreen(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Explore.route) { ExploreScreen(navController) }
            composable(Screen.MyBooking.route) {
                val context = LocalContext.current
                val database = AppDatabase.getDatabase(context)
                val repository = BookingRepository(database.bookingDao())
                val myBookingViewModel = remember { MyBookingViewModel(repository) }
                MyBookingScreen(myBookingViewModel)
            }
            composable(Screen.Setting.route) { SettingScreen(settingViewModel) }
            composable(Screen.Booking.route) { backstackEntry ->
                val destinationName = backstackEntry.arguments?.getString("destinationName") ?: "Điểm đến"
                val context = LocalContext.current
                val database = AppDatabase.getDatabase(context)
                val repository = BookingRepository(database.bookingDao())
                val application = context.applicationContext as Application
                val bookingViewModel = remember { BookingViewModel(application, repository) }
                BookingScreen(navController, destinationName,bookingViewModel)
            }

            composable(Screen.Detail.route) { backStackEntry ->
                val destinationName = backStackEntry.arguments?.getString("destinationName") ?: "Điểm đến"
                DetailScreen(navController, destinationName)
            }
        }
    }
}