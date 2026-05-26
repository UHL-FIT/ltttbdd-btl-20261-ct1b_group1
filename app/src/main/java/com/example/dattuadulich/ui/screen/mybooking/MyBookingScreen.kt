package com.example.dattuadulich.ui.screen.mybooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.res.Configuration

@Composable
fun MyBookingScreen() {
    // FIX 1: Thay Color(0xFF12121A) bằng màu background của hệ thống
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // FIX 2: Thay Color.White bằng onBackground để tự đổi Trắng/Đen
            Text(
                "Lịch sử đặt chỗ",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // FIX 3: Thay Color.Gray bằng onSurfaceVariant (màu xám chuẩn Material 3)
            Text(
                "Bạn chưa có chuyến đi nào gần đây.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// FIX 4: Thêm Preview để kiểm tra cả 2 chế độ Sáng và Tối
@Preview(showBackground = true, name = "Lịch Sử Đặt Chỗ")
@Composable
fun MyBookingScreenPreviewDark() {
    MyBookingScreen()
}