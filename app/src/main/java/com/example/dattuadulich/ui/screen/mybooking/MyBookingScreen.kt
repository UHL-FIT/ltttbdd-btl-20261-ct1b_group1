package com.example.dattuadulich.ui.screen.mybooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import android.content.Intent
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson

@Composable
fun MyBookingScreen(viewModel: MyBookingViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var editingBooking by remember { mutableStateOf<DatTourEntity?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        val context = LocalContext.current

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Lịch sử đặt chỗ",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            // Nút Xuất JSON
            IconButton(
                onClick = {
                    // [YÊU CẦU 3 - Xuất/nhập dữ liệu]: Xuất dữ liệu (JSON)
                    // 1. Biến danh sách hóa đơn thành chuỗi JSON
                    val jsonString = Gson().toJson(uiState.historyList)

                    // 2. Mở bảng Chia sẻ của điện thoại
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, jsonString)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, "Xuất dữ liệu JSON qua...")
                    context.startActivity(shareIntent)
                }
            ) {
                Icon(Icons.Default.Share, contentDescription = "Xuất JSON", tint = MaterialTheme.colorScheme.primary)
            }
        }
        // [YÊU CẦU 3 - Thống kê]: Thống kê dữ liệu (Tổng số bản ghi, Min/Max)
        // VẼ BẢNG THỐNG KÊ (Dùng các biến đã hứng ở trên)
        if (uiState.historyList.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("📊 BẢNG TỔNG KẾT", fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Tổng số tour đã đặt: ${uiState.tongSoTour} tour")

                    val tongTienStr = NumberFormat.getNumberInstance(Locale.forLanguageTag("vi-VN")).format(uiState.tongTien)
                    Text("• Tổng chi phí: $tongTienStr đ")

                    // THAY VÌ dùng trực tiếp maxTour, minTour → hứng vào local val trước
                    val maxTourLocal = uiState.maxTour  // local val → Kotlin có thể smart cast
                    val minTourLocal = uiState.minTour

                    if (maxTourLocal != null) {
                        val maxTienStr = NumberFormat.getNumberInstance(Locale.forLanguageTag("vi-VN")).format(maxTourLocal.tongTien)
                        Text("• Tour đắt nhất: ${maxTourLocal.tenDiaDiem} ($maxTienStr đ)")
                    }
                    if (minTourLocal != null) {
                        val minTienStr = NumberFormat.getNumberInstance(Locale.forLanguageTag("vi-VN")).format(minTourLocal.tongTien)
                        Text("• Tour rẻ nhất: ${minTourLocal.tenDiaDiem} ($minTienStr đ)")
                    }
                }
            }
        }
        if (uiState.historyList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "Bạn chưa có chuyến đi nào gần đây.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(uiState.historyList) { hoaDon ->
                    BookingCard(
                        hoaDon = hoaDon,
                        // [YÊU CẦU 3 - Chức năng]: Sửa (Update) và Xóa (Delete)
                        onEditClick = { editingBooking = hoaDon },
                        onCancelClick = { viewModel.xoaHoaDon(hoaDon) }
                    )
                }
            }
        }
    }

    // Hiển thị Dialog nếu đang chọn 1 hóa đơn để sửa
    editingBooking?.let { hoaDon ->
        UpdateBookingDialog(
            hoaDon = hoaDon,
            onDismiss = { editingBooking = null },
            onSave = { updatedSoNguoi, updatedNgay ->
                val donGia = hoaDon.tongTien / hoaDon.soNguoi
                val newTongTien = donGia * updatedSoNguoi
                val updatedHoaDon = hoaDon.copy(
                    soNguoi = updatedSoNguoi,
                    ngayKhoiHanh = updatedNgay,
                    tongTien = newTongTien
                )
                viewModel.capNhatHoaDon(updatedHoaDon)
                editingBooking = null
            }
        )
    }
}

@Composable
fun BookingCard(hoaDon: DatTourEntity, onEditClick: () -> Unit, onCancelClick: () -> Unit) {
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

                val giaTienStr = NumberFormat.getNumberInstance(Locale.forLanguageTag("vi-VN")).format(hoaDon.tongTien)
                Text("Tổng: $giaTienStr đ", color = Color(0xFFFF8C00), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            // Nút Sửa và Hủy
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Sửa Tour", tint = Color(0xFF1976D2))
                }
                IconButton(onClick = onCancelClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Hủy Tour", tint = Color.Red)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBookingDialog(
    hoaDon: DatTourEntity,
    onDismiss: () -> Unit,
    onSave: (Int, String) -> Unit
) {
    var numberOfPeople by remember { mutableStateOf(hoaDon.soNguoi.toString()) }
    var date by remember { mutableStateOf(hoaDon.ngayKhoiHanh) }
    
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            date = formatter.format(Date(it))
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cập nhật Hóa Đơn", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                OutlinedTextField(
                    value = numberOfPeople,
                    onValueChange = { numberOfPeople = it },
                    label = { Text("Số lượng người đi") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = date,
                    onValueChange = { },
                    label = { Text("Ngày khởi hành (Nhấn icon lịch)") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Chọn ngày")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val soNguoiInt = numberOfPeople.toIntOrNull() ?: 1
                onSave(soNguoiInt, date)
            }) {
                Text("Lưu Thay Đổi")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Chọn")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Hủy")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
