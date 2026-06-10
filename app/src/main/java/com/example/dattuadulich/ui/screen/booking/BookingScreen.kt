package com.example.dattuadulich.ui.screen.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController, destinationName: String, viewModel: BookingViewModel) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var numberOfPeople by remember { mutableStateOf("1") }
    var errorMessage by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    // DatePicker State
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            date = formatter.format(Date(it))
        }
    }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Tour: $destinationName",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val giaTienStr = java.text.NumberFormat.getNumberInstance(Locale("vi", "VN"))
                        .format(uiState.giaTien)
                    Text(
                        "Giá vé: $giaTienStr đ / người",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        "Thông tin người đặt",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Họ và tên") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Số điện thoại") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    // Ô chọn Ngày (Bấm vào sẽ hiện Lịch)
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = date,
                            onValueChange = { },
                            label = { Text("Ngày khởi hành") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.CalendarToday,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            readOnly = true, // Không cho gõ chữ
                            enabled = false, // Vô hiệu hóa để click xuyên qua Box
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        )
                        // Lớp phủ trong suốt để bắt sự kiện click
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable { showDatePicker = true }
                        )
                    }

                    OutlinedTextField(
                        value = numberOfPeople,
                        onValueChange = { numberOfPeople = it },
                        label = { Text("Số lượng người đi") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Text(
                        "Tổng cộng: $tongTienStr đ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFFFF8C00)
                    )

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Tổng tiền tính nhanh (Tính năng xịn xò của Thành viên 2)
                    val soNguoiInt = numberOfPeople.toIntOrNull() ?: 0
                    val tongTien = uiState.giaTien * soNguoiInt
                    val tongTienStr = java.text.NumberFormat.getNumberInstance(Locale("vi", "VN"))
                        .format(tongTien)

                    Text(
                        "Tổng cộng: $tongTienStr đ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFFFF8C00)
                    )

                    Button(
                        onClick = {
                            // CÁC CHỐT CHẶN BẢO VỆ ĐÃ ĐƯỢC ĐƠN GIẢN HÓA:
                            if (name.isBlank()) {
                                errorMessage = "Vui lòng nhập Họ và tên!"
                                return@Button
                            }
                            // Bàn phím số đã ngăn gõ chữ, chỉ cần kiểm tra đủ 10 số và bắt đầu bằng số 0
                            if (phoneNumber.length != 10 || !phoneNumber.startsWith("0")) {
                                errorMessage = "Số điện thoại phải có 10 số và bắt đầu bằng số 0!"
                                return@Button
                            }
                            if (date.isBlank()) {
                                errorMessage = "Vui lòng chọn ngày khởi hành!"
                                return@Button
                            }
                            val soNguoiInt = numberOfPeople.toIntOrNull()
                            if (soNguoiInt == null || soNguoiInt <= 0) {
                                errorMessage = "Số lượng người phải lớn hơn 0!"
                                return@Button
                            }

                            errorMessage = ""
                            viewModel.luuHoaDon(
                                tenDiadiem = destinationName,
                                anhDiaDiem = uiState.anhDiaDiem,
                                tenKhachHang = name,
                                sdtKhachHang = phoneNumber,
                                ngayKhoiHanh = date,
                                soNguoi = soNguoiInt,
                                onSucess = {
                                    navController.popBackStack()
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C00)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "XÁC NHẬN ĐẶT TOUR",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                // Hộp thoại Chọn Ngày (DatePickerDialog)
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("Xác nhận")
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
        }
    }
}
