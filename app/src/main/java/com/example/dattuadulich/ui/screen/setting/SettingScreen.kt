package com.example.dattuadulich.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen() {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF12121A)).padding(24.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(80.dp).clip(CircleShape).background(Color(0xFF2A2A38)))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Người dùng", color = Color.White, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Text("thanhvien@nhom.com", color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E29))
        ) {
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Settings, contentDescription = null, tint = Color(0xFFFFB74D))
                Spacer(modifier = Modifier.width(16.dp))
                Text("Cài đặt chung", color = Color.White, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Preview(showBackground = true, name = "Cài Đặt")
@Composable
fun SettingScreenPreview() {
    SettingScreen()
}