package com.example.dattuadulich

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dattuadulich.navigation.AppNavigation
import com.example.dattuadulich.ui.screen.setting.SettingViewModel
import com.example.dattuadulich.ui.theme.DattuadulichTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Kích hoạt trải nghiệm tràn viền (tùy chọn nhưng nên có)
        enableEdgeToEdge()
        setContent {
            // Khởi tạo ViewModel ở cấp cao nhất để quản lý State toàn app
            val settingViewModel: SettingViewModel = viewModel()

            // Lắng nghe trạng thái UI, quan trọng nhất là isDarkMode
            val uiState by settingViewModel.uiState.collectAsState()

            // 1. Truyền giá trị isDarkMode từ ViewModel vào Theme
            DattuadulichTheme(darkTheme = uiState.isDarkMode) {

                // 2. Surface giúp tự động áp dụng màu nền (background) theo Theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 3. Nếu bạn dùng Navigation (nhiều màn hình)
                    AppNavigation(settingViewModel)

                    // Hoặc nếu bạn chỉ muốn test riêng màn hình Setting:
                    // SettingScreen(viewModel = settingViewModel)
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity is started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity is resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity is paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity is stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity is destroyed")
    }
}