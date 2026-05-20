package com.example.dattuadulich

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ExploreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExploreScreen()
        }
    }
}
@Preview
@Composable
fun ExploreScreen() {

    val context = LocalContext.current

    // Danh sách địa điểm
    val destinations = listOf(

        Destination(
            name = "Đà Nẵng",
            imageRes = R.drawable.danang,
            bannerRes = R.drawable.banner_danang,
            description = "Đà Nẵng là thành phố biển đẹp nổi tiếng của Việt Nam."
        ),

        Destination(
            name = "Huế",
            imageRes = R.drawable.hue,
            bannerRes = R.drawable.banner_hue,
            description = "Huế nổi tiếng với vẻ đẹp cổ kính và thơ mộng."
        ),

        Destination(
            name = "Hà Nội",
            imageRes = R.drawable.hanoi,
            bannerRes = R.drawable.banner_hanoi,
            description = "Hà Nội là thủ đô nghìn năm văn hiến."
        ),

        Destination(
            name = "Quảng Ninh",
            imageRes = R.drawable.quangninh,
            bannerRes = R.drawable.banner_quangninh,
            description = "Quảng Ninh nổi tiếng với Vịnh Hạ Long."
        )
    )

    Box {

        // ===== Background =====
        Image(
            painter = painterResource(id = R.drawable.background_app),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // ===== Overlay =====
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        // ===== Nội dung =====
        Scaffold(

            containerColor = Color.Transparent,

            bottomBar = {
                BottomMenuBar(selectedIndex = 1)
            }

        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

                item {

                    Text(
                        text = "Khám Phá",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Các địa điểm du lịch nổi bật",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // ===== Danh sách địa điểm =====
                items(destinations) { destination ->

                    ExploreCard(

                        destination = destination,

                        onClick = {

                            val intent =
                                Intent(
                                    context,
                                    DetailActivity::class.java
                                )

                            intent.putExtra(
                                "destination_name",
                                destination.name
                            )

                            intent.putExtra(
                                "destination_desc",
                                destination.description
                            )

                            intent.putExtra(
                                "destination_image",
                                destination.bannerRes
                            )

                            context.startActivity(intent)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ExploreCard(
    destination: Destination,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable {

                onClick()
            },

        shape = RoundedCornerShape(20.dp)

    ) {

        Box {

            // ===== Ảnh =====
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // ===== Overlay tối =====
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
            )

            // ===== Tên địa điểm =====
            Text(
                text = destination.name,

                style = MaterialTheme.typography.headlineSmall,

                color = Color.White,

                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}