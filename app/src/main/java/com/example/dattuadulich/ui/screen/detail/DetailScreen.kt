package com.example.dattuadulich.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.delay

// 1. Model cho Chi tiết
data class DetailUiState(
    val isLoading: Boolean = true,
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val price: String = "Liên hệ",
    val rating: Double = 4.9
)

// 2. ViewModel giả lập gọi API
class DetailViewModel : ViewModel() {
    private val _uiState = mutableStateOf(DetailUiState())
    val uiState: State<DetailUiState> = _uiState

    fun fetchDetail(name: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Giả lập gọi API
        val mockData = when {
            name.contains("Hạ Long") -> DetailUiState(
                false, "Vịnh Hạ Long", 
                "https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?w=800&q=80",
                "Vịnh Hạ Long là di sản thiên nhiên thế giới được UNESCO công nhận, với hàng nghìn đảo đá vôi kỳ vĩ mọc lên từ làn nước xanh ngọc bích. Đây là điểm đến không thể bỏ qua khi tới Việt Nam."
            )
            name.contains("Đà Nẵng") -> DetailUiState(
                false, "Đà Nẵng",
                "https://images.unsplash.com/photo-1559592442-998818451124?w=800&q=80",
                "Thành phố của những cây cầu và bãi biển Mỹ Khê xinh đẹp. Đà Nẵng kết hợp hài hòa giữa nhịp sống hiện đại và vẻ đẹp tự nhiên hoang sơ."
            )
            name.contains("Đà Lạt") -> DetailUiState(
                false, "Đà Lạt",
                "https://images.unsplash.com/photo-1589182397057-b16174fe924d?w=800&q=80",
                "Thành phố ngàn hoa với không khí se lạnh quanh năm. Những đồi thông bạt ngàn và những thác nước hùng vĩ sẽ làm say lòng bất cứ du khách nào."
            )
            name.contains("Hà Nội") -> DetailUiState(
                false, "Thủ đô Hà Nội",
                "https://images.unsplash.com/photo-1555931466-17955c747d52?w=800&q=80",
                "Trái tim của Việt Nam với nghìn năm văn hiến. Hồ Gươm, Phố Cổ và những món ăn đường phố đặc sắc đang chờ bạn khám phá."
            )
            else -> DetailUiState(
                false, name,
                "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=80",
                "Khám phá vẻ đẹp tiềm ẩn tại $name. Một chuyến đi đầy hứa hẹn với những trải nghiệm tuyệt vời và kỷ niệm khó quên."
            )
        }
        _uiState.value = mockData
    }
}

@Composable
fun DetailScreen(
    navController: NavController, 
    destinationName: String,
    viewModel: DetailViewModel = viewModel()
) {
    val state by viewModel.uiState
    
    // Gọi API khi màn hình khởi tạo
    LaunchedEffect(destinationName) {
        viewModel.fetchDetail(destinationName)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            bottomBar = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 8.dp,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Button(
                        onClick = { /* Chức năng đặt tour */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Đặt Tour Ngay", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                // Image Header
                Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                    SubcomposeAsyncImage(
                        model = state.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize().background(Color.LightGray.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(40.dp))
                            }
                        },
                        error = {
                            Box(
                                modifier = Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Không thể tải ảnh", color = Color.Gray)
                            }
                        }
                    )
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.Black.copy(0.3f), RoundedCornerShape(12.dp))
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                }

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(state.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(20.dp))
                        Text(" ${state.rating} (Trải nghiệm tuyệt vời)", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Mô tả", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        state.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 24.sp
                    )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    // Thêm thông tin bổ sung
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoChip("Có xe đưa đón miễn phí")
                        InfoChip("Khách sạn 5 sao mường thanh")
                    }
                }
            }
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text, modifier = Modifier.padding(8.dp), fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondaryContainer)
    }
}
