import androidx.navigation.compose.composable

// 1. Định nghĩa các điểm đến (Route)
sealed class Screen(val route: String) {
    object LogIn : Screen("login")
    object Explore : Screen("explore")
    object Detail : Screen("detail/{tourId}") // Có thể truyền ID tour
}

@Composable
fun MyAppNavigation() {
    val navController = androidx.navigation.compose.rememberNavController() // "Người quản lý"

    // 2. Thiết lập sơ đồ chuyển màn hình
    androidx.navigation.NavHost(navController = navController, startDestination = Screen.LogIn.route) {

        // Màn hình Đăng nhập
        composable(Screen.LogIn.route) {
            LogInScreen(onLoginSuccess = {
                navController.navigate(Screen.Explore.route) // Chuyển sang Explore
            })
        }

        // Màn hình Khám phá
        composable(Screen.Explore.route) {
            ExploreScreen(onTourClick = { id ->
                navController.navigate("detail/$id") // Chuyển sang Detail với ID
            })
        }

        // Màn hình Chi tiết
        composable(Screen.Detail.route) { backStackEntry ->
            val tourId = backStackEntry.arguments?.getString("tourId")
            DetailScreen(tourId)
        }
    }
}