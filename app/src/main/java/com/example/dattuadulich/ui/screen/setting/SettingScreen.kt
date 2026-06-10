package com.example.dattuadulich.ui.screen.setting

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dattuadulich.ui.theme.DattuadulichTheme

@Composable
fun SettingScreen(viewModel: SettingViewModel = viewModel(), navController: androidx.navigation.NavController? = null) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    // FIX 1: Dùng Scaffold để StatusBar tự động điều chỉnh màu theo theme
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Fix: Đảm bảo không bị đè bởi thanh hệ thống
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // --- Avatar & thông tin user ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        uiState.userName,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(uiState.email, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Card cài đặt chung ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // FIX 2: Giữ màu Primary (Màu cam) cho Icon
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Cài đặt hệ thống",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            val sectionColor = MaterialTheme.colorScheme.primary

            // --- Nhóm 1: Tài khoản ---
            Text("Tài khoản", fontWeight = FontWeight.Bold, color = sectionColor)

            ClickableSettingRow("Chỉnh sửa hồ sơ", "Tên, email, số điện thoại") {
                Toast.makeText(context, "Mở chỉnh sửa hồ sơ", Toast.LENGTH_SHORT).show()
            }

            ClickableSettingRow("Đăng xuất", "Đăng xuất khỏi tài khoản") {
                Toast.makeText(context, "Đã đăng xuất", Toast.LENGTH_SHORT).show()
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 2: Thông báo ---
            Text("Thông báo", fontWeight = FontWeight.Bold, color = sectionColor)

            SwitchSettingRow(
                "Khuyến mãi tour",
                "Nhận thông báo giảm giá",
                uiState.notifyPromo
            ) { viewModel.updateNotifyPromo(it) }

            SwitchSettingRow(
                "Nhắc lịch khởi hành",
                "Nhắc trước 1 ngày",
                uiState.notifyReminder
            ) { viewModel.updateNotifyReminder(it) }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 3: Ngôn ngữ & Giao diện ---
            Text("Ngôn ngữ & Giao diện", fontWeight = FontWeight.Bold, color = sectionColor)

            ClickableSettingRow("Ngôn ngữ", "Đang dùng: ${uiState.selectedLanguage}") {
                Toast.makeText(context, "Chọn ngôn ngữ", Toast.LENGTH_SHORT).show()
            }

            SwitchSettingRow(
                "Chế độ tối",
                "Giao diện nền tối",
                uiState.isDarkMode
            ) { viewModel.updateDarkMode(it) }

            ClickableSettingRow("Kiểu bản đồ", "Mặc định, vệ tinh, địa hình") {
                Toast.makeText(context, "Chọn kiểu bản đồ", Toast.LENGTH_SHORT).show()
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 4: Quyền riêng tư ---
            Text("Quyền riêng tư", fontWeight = FontWeight.Bold, color = sectionColor)

            ClickableSettingRow("Cho phép định vị", "GPS để dẫn đường") {
                val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = "package:${context.packageName}".toUri()
                context.startActivity(intent)
            }
            ClickableSettingRow("Chính sách bảo mật", "Quy định dữ liệu cá nhân") {
                val url = "https://www.example.com/privacy".toUri()
                context.startActivity(Intent(Intent.ACTION_VIEW, url))
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant // Màu đường kẻ tự thích ứng
            )


            // --- Nhóm 5: Thông tin ứng dụng ---
            Text("Thông tin ứng dụng", fontWeight = FontWeight.Bold, color = sectionColor)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Phiên bản", fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                    Text(uiState.appVersion, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                }
            }

            ClickableSettingRow("Giới thiệu ứng dụng", "Thông tin về ứng dụng này") {
                navController?.navigate(com.example.dattuadulich.navigation.Screen.About.route)
            }

            ClickableSettingRow("Đánh giá ứng dụng", "Ủng hộ 5 sao trên Store") {
                val packageName = context.packageName
                try {
                    context.startActivity(Intent(Intent.ACTION_VIEW, "market://details?id=$packageName".toUri()))
                } catch (_: Exception) {
                    context.startActivity(Intent(Intent.ACTION_VIEW, "https://play.google.com/store/apps/details?id=$packageName".toUri()))
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 6: Hỗ trợ ---
            Text("Hỗ trợ", fontWeight = FontWeight.Bold, color = sectionColor)

            ClickableSettingRow("Gửi phản hồi", "Báo lỗi hoặc góp ý") {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:support@tourdulich.com".toUri()
                    putExtra(Intent.EXTRA_SUBJECT, "Phản hồi ứng dụng")
                }
                context.startActivity(Intent.createChooser(emailIntent, "Gửi mail qua..."))
            }

            ClickableSettingRow("Gọi tổng đài", "1800 1234 (miễn phí)") {
                context.startActivity(Intent(Intent.ACTION_DIAL).apply { data = "tel:18001234".toUri() })
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ClickableSettingRow(title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
            Text(subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun SwitchSettingRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
            Text(subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview(showBackground = true, name = "Cài Đặt")
@Composable
fun SettingScreenPreview() {
    // Bọc trong Theme để thấy được màu Primary cam và nền tối
    DattuadulichTheme(darkTheme = true) {
        SettingScreen()
    }
}