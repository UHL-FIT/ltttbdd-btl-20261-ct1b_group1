package com.example.dattuadulich.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarScreen(navController: NavController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route // lấy cái biến route bên trong cái trang hiện tại

    val bottomBarScreens = listOf(Screen.Home, Screen.Explore, Screen.MyBooking, Screen.Setting)

    NavigationBar(
        // Sử dụng màu surface từ Theme thay vì ép cứng
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        bottomBarScreens.forEach { screen ->
            val isSelected = currentRoute == screen.route // kiểm tra current hiện tại với route trong list item trong bottombarscreen

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(screen.title) },
                colors = NavigationBarItemDefaults.colors(
                    // Màu khi được chọn: Nên dùng màu Primary của hệ thống hoặc màu cam bạn thích
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,

                    // Màu khi KHÔNG được chọn: Dùng onSurfaceVariant để tự đổi theo sáng/tối
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,

                    // Màu của vòng tròn bao quanh icon khi được chọn
                    // secondaryContainer sẽ tự động đổi từ xám nhạt (Light) sang xám đậm (Dark)
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    }
}
