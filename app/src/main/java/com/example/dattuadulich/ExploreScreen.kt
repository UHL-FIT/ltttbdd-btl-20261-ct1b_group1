package com.example.dattuadulich

import android.content.Intent
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
import androidx.navigation.NavHostController

@Composable
fun ExploreScreen(navController: NavHostController) {

    val context = LocalContext.current

    val destinations = listOf(

        Destination(
            name = "Đà Nẵng",
            imageRes = R.drawable.danang,
            bannerRes = R.drawable.banner_danang,
            description =
                "Đà Nẵng là một trong những thành phố biển đẹp và đáng sống nhất Việt Nam."
        ),

        Destination(
            name = "Huế",
            imageRes = R.drawable.hue,
            bannerRes = R.drawable.banner_hue,
            description =
                "Huế là thành phố cổ kính nổi tiếng với vẻ đẹp trầm mặc."
        ),

        Destination(
            name = "Hà Nội",
            imageRes = R.drawable.hanoi,
            bannerRes = R.drawable.banner_hanoi,
            description =
                "Hà Nội là thủ đô nghìn năm văn hiến của Việt Nam."
        ),

        Destination(
            name = "Quảng Ninh",
            imageRes = R.drawable.quangninh,
            bannerRes = R.drawable.banner_quangninh,
            description =
                "Quảng Ninh nổi tiếng với kỳ quan thiên nhiên Vịnh Hạ Long."
        )
    )

    Box {

        Image(
            painter = painterResource(id = R.drawable.background_app),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Scaffold(

            containerColor = Color.Transparent,

            bottomBar = {
                BottomMenuBar(
                    selectedIndex = 1,
                    navController = navController
                )
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

            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
            )

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

@Preview(showBackground = true)
@Composable
fun PreviewExploreScreen() {

    Text("Preview")
}