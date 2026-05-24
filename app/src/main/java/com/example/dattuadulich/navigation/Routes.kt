//khai báo route app
package com.example.dattuadulich.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

// Chứa danh sách các địa chỉ trong App
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Trang chủ", Icons.Default.Home)
    object Explore : Screen("explore", "Khám phá", Icons.Default.Explore)
    object MyBooking : Screen("my_booking", "Lịch sử", Icons.Default.History)
    object Setting : Screen("setting", "Tài khoản", Icons.Default.Person)
    object Detail : Screen("detail/{destinationName}", "Chi tiết", Icons.Default.Explore)
}