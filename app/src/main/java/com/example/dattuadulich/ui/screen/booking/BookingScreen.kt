package com.example.dattuadulich.ui.screen.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController, tourImage: String, destinationName: String, viewModel: BookingViewModel) {
    // 1. Giữ các biến local của bạn
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var numberOfPeople by remember { mutableStateOf("1") }

    // 2. Lấy UiState thay vì lấy lẻ tẻ
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(destinationName) {
        viewModel.taiThongTinTour(destinationName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Đặt Tour", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Bọc trong Box để hiện Loading nếu cần
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Thẻ thông tin Tour (Giữ nguyên giao diện của bạn)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Tour: $destinationName", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(8.dp))

                            // Định dạng tiền từ uiState.giaTien
                            val giaTienStr = NumberFormat.getNumberInstance(Locale("vi", "VN")).format(uiState.giaTien)
                            Text("Giá vé: $giaTienStr đ / người", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                    }

                    Text("Thông tin người đặt", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                    // Các TextField (Giữ nguyên của bạn)
                    OutlinedTextField(
                        value = name, onValueChange = { name = it },
                        label = { Text("Họ và tên") },
                        leadingIcon = { Icon(Icons.Default.Person, null) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = phoneNumber, onValueChange = { phoneNumber = it },
                        label = { Text("Số điện thoại") },
                        leadingIcon = { Icon(Icons.Default.Phone, null) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = date, onValueChange = { date = it },
                        label = { Text("Ngày khởi hành (VD: 20/10/2024)") },
                        leadingIcon = { Icon(Icons.Default.CalendarToday, null) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = numberOfPeople, onValueChange = { numberOfPeople = it },
                        label = { Text("Số lượng người đi") },
                        leadingIcon = { Icon(Icons.Default.Person, null) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Tổng tiền tính nhanh
                    val soNguoiInt = numberOfPeople.toIntOrNull() ?: 1
                    val tongTien = uiState.giaTien * soNguoiInt
                    val tongTienStr = NumberFormat.getNumberInstance(Locale("vi", "VN")).format(tongTien)

                    Text("Tổng cộng: $tongTienStr đ", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFFFF8C00))

                    Button(
                        onClick = {
                            viewModel.luuHoaDon(
                                tenDiadiem = destinationName,
                                anhDiaDiem = tourImage,
                                tenKhachHang = name,
                                sdtKhachHang = phoneNumber,
                                ngayKhoiHanh = date,
                                soNguoi = soNguoiInt,
                                onSucess = { navController.popBackStack() }
                            )
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C00)),
                        shape = RoundedCornerShape(8.dp),
                        enabled = name.isNotBlank() && phoneNumber.isNotBlank()
                    ) {
                        Text("XÁC NHẬN ĐẶT TOUR", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}