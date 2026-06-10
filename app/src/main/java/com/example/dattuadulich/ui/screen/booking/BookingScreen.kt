package com.example.dattuadulich.ui.screen.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController, destinationName: String, viewModel: BookingViewModel) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var numberOfPeople by remember { mutableStateOf("1") }
    var errorMessage by remember { mutableStateOf("") }
    val giaTien by viewModel.giaTien.collectAsState()

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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF8FAFC)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Thẻ thông tin Tour
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Tour: $destinationName", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    // Format giá tiền cho đẹp (VD: 1890000.0 -> 1,890,000)
                    val giaTienStr = java.text.NumberFormat.getNumberInstance(java.util.Locale.forLanguageTag("vi-VN")).format(giaTien)
                    Text("Giá vé: $giaTienStr đ / người", color = Color(0xFFFF8C00), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            Text("Thông tin  người đặt", fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))

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
                singleLine = true
            )

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Ngày khởi hành (VD: 20/10/2024)") },
                leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = numberOfPeople,
                onValueChange = { numberOfPeople = it },
                label = { Text("Số lượng người đi") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            // THÊM ĐOẠN NÀY VÀO NGAY TRÊN NÚT BẤM (Để hiện chữ màu đỏ nếu có lỗi)
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    // CÁC CHỐT CHẶN BẢO VỆ:
                    // 1. Kiểm tra Tên (Cơ bản là không được để trống)
                    if (name.isBlank()) {
                        errorMessage = "Vui lòng nhập Họ và tên!"
                        return@Button
                    }
                    // 2. Kiểm tra Số điện thoại (Phải bắt đầu bằng số 0 và đúng 10 chữ số)
                    // Regex "^0\\d{9}$" nghĩa là: Chữ cái đầu là số 0, theo sau là đúng 9 chữ số.
                    if (!phoneNumber.matches(Regex("^0\\d{9}$"))) {
                        errorMessage = "Số điện thoại phải có 10 số và bắt đầu bằng số 0!"
                        return@Button
                    }
                    // 3. Kiểm tra Ngày đi (Phải đúng định dạng DD/MM/YYYY)
                    // Regex "^\\d{2}/\\d{2}/\\d{4}$" nghĩa là: 2 số / 2 số / 4 số.
                    if (!date.matches(Regex("^\\d{2}/\\d{2}/\\d{4}$"))) {
                        errorMessage = "Ngày khởi hành phải ghi đúng kiểu DD/MM/YYYY (VD: 20/10/2024)!"
                        return@Button
                    }
                    // 4. Kiểm tra Số người (Phải là số và > 0)
                    val soNguoiInt = numberOfPeople.toIntOrNull()
                    if (soNguoiInt == null || soNguoiInt <= 0) {
                        errorMessage = "Số lượng người phải là số nguyên lớn hơn 0!"
                        return@Button
                    }
                    // Nếu qua được hết các chốt chặn thì xóa lỗi và cho phép lưu
                    errorMessage = ""
                    viewModel.luuHoaDon(
                        tenDiadiem = destinationName,
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
                Text("XÁC NHẬN ĐẶT TOUR", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun PreviewBookingScreen() {
//     MaterialTheme {
//         BookingScreen(rememberNavController(), "Vịnh Hạ Long", /* cần mock viewModel ở đây */)
//     }
// }
