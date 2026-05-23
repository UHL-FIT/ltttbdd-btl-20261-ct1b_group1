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

@Composable
fun MyBookingScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF12121A)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Lịch sử đặt chỗ", color = Color.White, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Bạn chưa có chuyến đi nào gần đây.", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, name = "Lịch Sử")
@Composable
fun MyBookingScreenPreview() {
    MyBookingScreen()
}