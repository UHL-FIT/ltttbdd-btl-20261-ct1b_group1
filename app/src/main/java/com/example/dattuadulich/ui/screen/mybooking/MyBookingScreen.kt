package com.example.dattuadulich.ui.screen.mybooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dattuadulich.data.local.DatTourEntity
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MyBookingScreen(viewModel: MyBookingViewModel) {
    val historyList by viewModel.historyList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            "Lịch sử đặt chỗ",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (historyList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "Bạn chưa có chuyến đi nào gần đây.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(historyList) { hoaDon ->
                    BookingCard(
                        hoaDon = hoaDon,
                        onCancelClick = { viewModel.xoaHoaDon(hoaDon) }
                    )
                }
            }
        }
    }
}

@Composable
fun BookingCard(hoaDon: DatTourEntity, onCancelClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // Ảnh Tour
            AsyncImage(
                model = hoaDon.anhDiaDiem,
                contentDescription = "Ảnh ${hoaDon.tenDiaDiem}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Thông tin
            Column(modifier = Modifier.weight(1f)) {
                Text(hoaDon.tenDiaDiem, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Người đặt: ${hoaDon.tenKhachHang} (${hoaDon.soNguoi} người)", fontSize = 14.sp)
                Text("Ngày đi: ${hoaDon.ngayKhoiHanh}", fontSize = 14.sp)

                val giaTienStr = NumberFormat.getNumberInstance(Locale("vi", "VN")).format(hoaDon.tongTien)
                Text("Tổng: $giaTienStr đ", color = Color(0xFFFF8C00), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            // Nút Hủy
            IconButton(onClick = onCancelClick) {
                Icon(Icons.Default.Delete, contentDescription = "Hủy Tour", tint = Color.Red)
            }
        }
    }
}
