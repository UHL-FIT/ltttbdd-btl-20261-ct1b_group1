package com.example.dattuadulich

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.core.net.toUri
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dattuadulich.ui.theme.DattuadulichTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DattuadulichTheme {
                SettingsScreen()
            }
        }
    }

    @Composable
    fun SettingsScreen() {
        val context = LocalContext.current

        var notifyPromo by remember { mutableStateOf(true) }
        var notifyReminder by remember { mutableStateOf(true) }
        var notifyWeather by remember { mutableStateOf(false) }
        var darkMode by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Cài đặt",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Nhóm 1: Tài khoản ---
            Text(
                text = "Tài khoản",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast.makeText(context, "Mở chỉnh sửa hồ sơ", Toast.LENGTH_SHORT).show()
                    }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Chỉnh sửa hồ sơ", fontWeight = FontWeight.Medium)
                    Text(
                        "Tên, email, số điện thoại",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast.makeText(context, "Đã đăng xuất", Toast.LENGTH_SHORT).show()
                    }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Đăng xuất", fontWeight = FontWeight.Medium)
                    Text(
                        "Đăng xuất khỏi tài khoản",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 2: Thông báo ---
            Text(
                text = "Thông báo",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            SwitchSettingRow(
                title = "Khuyến mãi tour",
                subtitle = "Nhận thông báo giảm giá",
                checked = notifyPromo
            ) {
                notifyPromo = it
            }
            SwitchSettingRow(
                title = "Nhắc lịch khởi hành",
                subtitle = "Nhắc trước 1 ngày",
                checked = notifyReminder
            ) {
                notifyReminder = it
            }
            SwitchSettingRow(
                title = "Thời tiết điểm đến",
                subtitle = "Cập nhật thời tiết",
                checked = notifyWeather
            ) {
                notifyWeather = it
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 3 & 4: Ngôn ngữ & Giao diện ---
            Text(
                text = "Ngôn ngữ & Giao diện",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            ClickableSettingRow(title = "Ngôn ngữ",
                                subtitle = "Chọn ngôn ngữ hiển thị") {
                Toast.makeText(context, "Chọn ngôn ngữ", Toast.LENGTH_SHORT).show()
            }
            SwitchSettingRow(
                title = "Chế độ tối",
                subtitle = "Giao diện nền tối",
                checked = darkMode
            ) {
                darkMode = it
            }
            ClickableSettingRow(title = "Kiểu bản đồ",
                                subtitle = "Mặc định, vệ tinh, địa hình") {
                Toast.makeText(context, "Chọn kiểu bản đồ", Toast.LENGTH_SHORT).show()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 6: Quyền riêng tư ---
            Text(
                text = "Quyền riêng tư",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            ClickableSettingRow(title = "Cho phép định vị",
                                subtitle = "GPS để dẫn đường") {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = "package:${context.packageName}".toUri()
                context.startActivity(intent)
            }
            ClickableSettingRow(
                title = "Chính sách bảo mật",
                subtitle = "Quy định dữ liệu cá nhân"
            ) {
                val url = "https://www.example.com/privacy".toUri()
                context.startActivity(Intent(Intent.ACTION_VIEW, url))
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 7: Thông tin ứng dụng ---
            Text(
                text = "Thông tin ứng dụng",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Phiên bản", fontWeight = FontWeight.Medium)
                    Text(
                        "1.0.0",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            ClickableSettingRow(title = "Đánh giá ứng dụng",
                                subtitle = "Ủng hộ 5 sao") {
                val packageName = context.packageName
                try {
                    val uri = "market://details?id=$packageName".toUri()
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                } catch (_: Exception) {
                    val uri = "https://play.google.com/store/apps/details?id=$packageName".toUri()
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            }

            ClickableSettingRow(title = "Chia sẻ ứng dụng",
                                subtitle = "Gửi link cho bạn bè") {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Chia sẻ app Tour Du Lịch")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Tải app tại: https://play.google.com/store/apps/details?id=${context.packageName}"
                    )
                }
                context.startActivity(Intent.createChooser(shareIntent, "Chia sẻ qua"))
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // --- Nhóm 8: Hỗ trợ ---
            Text(
                text = "Hỗ trợ",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            ClickableSettingRow(title = "Gửi phản hồi",
                                subtitle = "Báo lỗi hoặc góp ý") {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:support@tourdulich.com".toUri()
                    putExtra(Intent.EXTRA_SUBJECT, "Phản hồi ứng dụng Tour")
                }
                if (emailIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(emailIntent)
                } else {
                    Toast.makeText(context, "Không có ứng dụng email",
                        Toast.LENGTH_SHORT).show()
                }
            }

            ClickableSettingRow(title = "Câu hỏi thường gặp",
                subtitle = "Giải đáp thắc mắc") {
                val uri = "https://www.example.com/faq".toUri()
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            }

            ClickableSettingRow(title = "Gọi tổng đài",
                subtitle = "1800 1234 (miễn phí)") {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:18001234".toUri()
                }
                context.startActivity(callIntent)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    @Composable
    fun ClickableSettingRow(title: String,
                            subtitle: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Medium)
                Text(
                    subtitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
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
                Text(title, fontWeight = FontWeight.Medium)
                Text(
                    subtitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Switch(checked = checked, onCheckedChange = onCheckedChange)
        }
    }
}