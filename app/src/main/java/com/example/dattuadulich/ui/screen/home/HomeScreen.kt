package com.example.dattuadulich.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF12121A))) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Địa điểm Việt Nam \uD83C\uDDFB\uD83C\uDDF3",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchBarUI()
            Spacer(modifier = Modifier.height(24.dp))
            Text("Địa điểm nổi bật", style = MaterialTheme.typography.titleLarge, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalPagerUI(navController)
        }
    }
}

@Composable
fun SearchBarUI() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Bạn muốn đi đâu?", color = Color.Gray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF1E1E29),
            focusedContainerColor = Color(0xFF2A2A38),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerUI(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val destinations = listOf("Đà Nẵng", "Hà Nội", "Hồ Chí Minh", "Nha Trang")

    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().height(220.dp)) { page ->
        Card(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp).clickable {
                navController.navigate("detail/${destinations[page]}")
            },
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize().background(
                Brush.linearGradient(listOf(Color(0xFFFFB74D), Color(0xFFF57C00))) // Đổ gradient rất đẹp
            )) {
                Text(
                    text = destinations[page],
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Trang Chủ")
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
