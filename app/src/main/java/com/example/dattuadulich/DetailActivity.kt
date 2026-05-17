package com.example.dattuadulich

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ===== NHẬN DỮ LIỆU =====
        val name =
            intent.getStringExtra("destination_name")
                ?: "Không có tên"

        val desc =
            intent.getStringExtra("destination_desc")
                ?: "Không có mô tả"

        val image =
            intent.getIntExtra("destination_image", 0)

        setContent {

            MaterialTheme {

                DetailScreen(
                    name = name,
                    desc = desc,
                    image = image
                )
            }
        }
    }
}

@Composable
fun DetailScreen(
    name: String,
    desc: String,
    image: Int
) {

    val context = LocalContext.current

    Box {

        // ===== BACKGROUND =====
        Image(
            painter = painterResource(id = R.drawable.background_app),
            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.Crop
        )

        // ===== OVERLAY =====
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.25f)
                )
        )

        // ===== NỘI DUNG =====
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // ===== PHẦN CUỘN =====
            Column(

                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )

            ) {

                // ===== ẢNH =====
                Box {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp),

                        contentScale = ContentScale.Crop
                    )

                    // ===== OVERLAY ẢNH =====
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Color.Black.copy(alpha = 0.2f)
                            )
                    )

                    // ===== NÚT BACK =====
                    IconButton(

                        onClick = {

                            (context as? ComponentActivity)
                                ?.finish()
                        },

                        modifier = Modifier
                            .padding(16.dp)
                            .background(
                                Color.Black.copy(alpha = 0.55f),
                                shape = RoundedCornerShape(50)
                            )
                            .align(Alignment.TopStart)

                    ) {

                        Text(
                            text = "←",

                            color = Color.White,

                            style =
                                MaterialTheme
                                    .typography
                                    .headlineSmall
                        )
                    }

                    // ===== TÊN ĐỊA ĐIỂM TRÊN ẢNH =====
                    Text(

                        text = name,

                        color = Color.White,

                        style =
                            MaterialTheme
                                .typography
                                .headlineLarge,

                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    )
                }

                // ===== CARD THÔNG TIN =====
                Column(

                    modifier = Modifier
                        .padding(20.dp)
                        .background(
                            Color.Black.copy(alpha = 0.45f),

                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(24.dp)

                ) {

                    Text(
                        text = "Thông tin địa điểm",

                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,

                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // ===== DESCRIPTION =====
                    Text(

                        text = desc,

                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge,

                        color = Color.White
                    )
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            // ===== BUTTONS =====
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                Button(

                    onClick = {},

                    modifier = Modifier.weight(1f),

                    shape = RoundedCornerShape(14.dp)

                ) {

                    Text("ĐẶT TOUR")
                }

                OutlinedButton(

                    onClick = {},

                    modifier = Modifier.weight(1f),

                    shape = RoundedCornerShape(14.dp)

                ) {

                    Text("GỢI Ý")
                }
            }
        }
    }
}









// ================= PREVIEW =================

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {

    DetailScreen(

        name = "Đà Nẵng",

        desc =
            "Đà Nẵng là thành phố biển nổi tiếng của Việt Nam với nhiều địa điểm du lịch đẹp.",

        image = R.drawable.banner_danang
    )
}