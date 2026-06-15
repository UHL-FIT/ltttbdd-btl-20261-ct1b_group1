package com.example.dattuadulich

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
        Log.d("AppLifecycle", "onCreate")
        // Kích hoạt trải nghiệm tràn viền (tùy chọn nhưng nên có)
        enableEdgeToEdge()
        setContent {
            // Khởi tạo ViewModel ở cấp cao nhất để quản lý State toàn app
            val settingViewModel: SettingViewModel = viewModel()

            // Lắng nghe trạng thái UI, quan trọng nhất là isDarkMode
            val uiState by settingViewModel.uiState.collectAsState()

            // [YÊU CẦU CƠ BẢN 1]: Áp dụng MaterialTheme thống nhất (màu, font, shape)
            // 1. Truyền giá trị isDarkMode từ ViewModel vào Theme
            DattuadulichTheme(darkTheme = uiState.isDarkMode) {

                // 2. Surface giúp tự động áp dụng màu nền (background) theo Theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 3. Nếu bạn dùng Navigation (nhiều màn hình)
                    AppNavigation(settingViewModel)

                }
            }
        }
    }
    // [YÊU CẦU CƠ BẢN 2]: Override và ghi log (Logcat) 6 callback vòng đời
    override fun onStart() {
        super.onStart()
        Log.d("AppLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("AppLifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AppLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AppLifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AppLifecycle", "onDestroy")
    }
}