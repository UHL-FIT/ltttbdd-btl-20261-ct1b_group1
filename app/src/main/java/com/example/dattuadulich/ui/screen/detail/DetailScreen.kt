package com.example.dattuadulich.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(navController: NavController, destinationName: String) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF12121A))) {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp).background(
            Brush.verticalGradient(listOf(Color(0xFF2A2A38), Color(0xFF1E1E29)))
        )) {
            IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 32.dp, start = 16.dp)) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = destinationName,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFD700))
                Text("4.9", color = Color.White, modifier = Modifier.padding(start = 4.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Một trong những điểm đến hấp dẫn nhất với khung cảnh thiên nhiên hùng vĩ và trải nghiệm văn hóa độc đáo. Các thành viên có thể tải ảnh thật vào đây sau.",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /* Xử lý Đặt ngay */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB74D)),
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Đặt ngay", color = Color.Black, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Preview(showBackground = true, name = "Chi Tiết Điểm Đến")
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(), destinationName = "Vịnh Hạ Long")
}