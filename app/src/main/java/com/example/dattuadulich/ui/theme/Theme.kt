package com.example.dattuadulich.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Định nghĩa màu cho Chế độ tối (Giống tone màu bạn đang dùng ở Setting)
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB74D), // Màu cam nhấn (Orange)
    background = Color(0xFF12121A), // Nền tối
    surface = Color(0xFF1E1E2A),    // Nền các Card
    onBackground = Color.White,
    onSurface = Color.White
)

// Định nghĩa màu cho Chế độ sáng
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF57C00),
    background = Color(0xFFF5F5F5), // Nền xám nhạt
    surface = Color.White,          // Nền các Card trắng
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun DattuadulichTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Tắt dynamicColor nếu muốn app luôn dùng màu cam/đen bạn đã chọn
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}