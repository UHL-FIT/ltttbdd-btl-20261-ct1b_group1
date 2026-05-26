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
    // FIX 1: Thay Color(0xFF12121A) bằng background của hệ thống
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp).background(
            // FIX 2: Thay màu gradient tối bằng màu surface của hệ thống
            Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.surface))
        )) {
            IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 32.dp, start = 16.dp)) {
                // FIX 3: Thay Color.White bằng onSurface
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = destinationName,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    // FIX 4: Thay Color.White bằng onBackground
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFD700))
                // FIX 5: Thay Color.White bằng onBackground
                Text("4.9", color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(start = 4.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Một trong những điểm đến hấp dẫn nhất với khung cảnh thiên nhiên hùng vĩ và trải nghiệm văn hóa độc đáo. Các thành viên có thể tải ảnh thật vào đây sau.",
                // FIX 6: Thay Color.LightGray bằng onSurfaceVariant
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /* Xử lý Đặt ngay */ },
                // FIX 7: Nên dùng primary của hệ thống thay vì màu cam cố định nếu muốn đổi theme mượt mà
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    "Đặt ngay",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Chi Tiết Điểm Đến")
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(), destinationName = "Vịnh Hạ Long")
}