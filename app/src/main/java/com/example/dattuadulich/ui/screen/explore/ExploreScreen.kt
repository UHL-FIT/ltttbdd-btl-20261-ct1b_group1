package com.example.dattuadulich.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.dattuadulich.BottomMenuBar
import com.example.dattuadulich.data.remote.dto.Main
import com.example.dattuadulich.data.remote.dto.TourModel
import com.example.dattuadulich.data.remote.dto.Weather
import com.example.dattuadulich.data.remote.dto.WeatherResponse
import com.example.dattuadulich.data.remote.dto.Wind

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController? = null
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomMenuBar(selectedIndex = 0, navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC)))

            when (val state = uiState) {
                is HomeUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is HomeUiState.Error -> Text(state.message, modifier = Modifier.align(Alignment.Center), color = Color.Red)
                is HomeUiState.Success -> HomeContent(state.weather, state.tours)
            }
        }
    }
}

@Composable
fun HomeContent(weather: WeatherResponse, tours: List<TourModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- LAYOUT TRÊN (Khám phá + Thời tiết + Lên kế hoạch) ---
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Khám phá", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp))
                    Text("Quảng Ninh", fontSize = 14.sp)
                }
            }
        }

        item { WeatherHeaderCard(weather) }

        item { PlanTripCard() }

        // --- LAYOUT DƯỚI (Danh sách tour) ---
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Khám phá các tour nổi bật", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Xem tất cả >", fontSize = 12.sp, color = Color.Gray)
            }
        }

        items(tours) { tour ->
            TourItemCard(tour)
        }
    }
}

@Composable
fun WeatherHeaderCard(weather: WeatherResponse) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/${weather.weather.firstOrNull()?.icon}@2x.png",
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Text("${weather.main.temp.toInt()}°C", fontSize = 36.sp, fontWeight = FontWeight.Bold)
                    Text(weather.weather.firstOrNull()?.description ?: "Nắng nhẹ", color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Thứ Hai", fontWeight = FontWeight.Bold)
                    Text("20/05/2024", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Cập nhật: 09:30", fontSize = 10.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                WeatherInfoItem(Icons.Default.WaterDrop, "${weather.main.humidity}%")
                WeatherInfoItem(Icons.Default.Air, "${weather.wind.speed} km/h")
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.5f))

            Text("Thời tiết hôm nay", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                items(6) { i ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("${8 + i * 3}:00", fontSize = 10.sp, color = Color.Gray)
                        Icon(Icons.Default.WbSunny, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color(0xFFFFA500))
                        Text("2${6+i}°C", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun PlanTripCard() {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Lên kế hoạch chuyến đi", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = "20/05/2024",
                    onValueChange = {},
                    label = { Text("Ngày đi", fontSize = 10.sp) },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Icon(Icons.Default.CalendarToday, null, modifier = Modifier.size(16.dp)) }
                )
                Icon(Icons.AutoMirrored.Filled.ArrowForward, null, modifier = Modifier.padding(horizontal = 8.dp).size(20.dp))
                OutlinedTextField(
                    value = "23/05/2024",
                    onValueChange = {},
                    label = { Text("Ngày về", fontSize = 10.sp) },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Icon(Icons.Default.CalendarMonth, null, modifier = Modifier.size(16.dp)) }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Icon(Icons.Default.Lightbulb, null, modifier = Modifier.size(20.dp), tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Gợi ý lịch trình & ngày nên đi", color = Color.Black)
            }
        }
    }
}

@Composable
fun TourItemCard(tour: TourModel) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = tour.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(100.dp).background(Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                Text(tour.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                    Text(tour.location, fontSize = 11.sp, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                    Text(" 2 ngày 1 đêm", fontSize = 11.sp, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, modifier = Modifier.size(12.dp), tint = Color(0xFFFFB300))
                    Text(" ${tour.rating} (120 đánh giá)", fontSize = 11.sp, color = Color.Gray)
                }
                Text(
                    text = tour.price,
                    modifier = Modifier.align(Alignment.End),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun WeatherInfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, modifier = Modifier.size(16.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(value, fontSize = 12.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    val mockWeather = WeatherResponse(
        main = Main(temp = 28.0, humidity = 64),
        weather = listOf(Weather(description = "Nắng nhẹ", icon = "01d")),
        wind = Wind(speed = 10.0),
        name = "Quảng Ninh"
    )
    val mockTours = listOf(
        TourModel(1, "Hạ Long – Kỳ quan thiên nhiên", "Quảng Ninh", "1.890.000đ", "https://i.ibb.co/v4S8L8Y/halong.jpg", 4.8),
        TourModel(2, "Bãi Cháy – Sun World Hạ Long", "Quảng Ninh", "950.000đ", "https://i.ibb.co/mS6p0v3/baichay.jpg", 4.6),
        TourModel(3, "Yên Tử – Chốn thiền linh thiêng", "Quảng Ninh", "850.000đ", "https://i.ibb.co/fN0mP2Z/yentu.jpg", 4.7)
    )
    MaterialTheme {
        HomeContent(weather = mockWeather, tours = mockTours)
    }
}