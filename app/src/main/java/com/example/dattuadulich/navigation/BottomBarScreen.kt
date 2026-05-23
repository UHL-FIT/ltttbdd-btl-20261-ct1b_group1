package com.example.dattuadulich.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarScreen(navController: NavController) {
    // Theo dõi xem navController đang đứng ở địa chỉ nào
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route

    // Danh sách các màn hình cần gắn vào thanh menu dưới đáy (Đã bỏ Booking)
    val bottomBarScreens = listOf(Screen.Home, Screen.Explore, Screen.MyBooking, Screen.Setting)

    // Bỏ điều kiện if, hiển thị thanh menu ở mọi nơi (kể cả DetailScreen)
    NavigationBar(containerColor = Color(0xFF1E1E29), contentColor = Color.White) {
        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFB74D), // Màu cam
                    selectedTextColor = Color(0xFFFFB74D),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color(0xFF2A2A38)
                )
            )
        }
    }
}