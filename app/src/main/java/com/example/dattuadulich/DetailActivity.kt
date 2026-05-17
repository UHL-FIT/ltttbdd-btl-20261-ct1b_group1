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
import androidx.compose.ui.unit.dp

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // NHẬN DỮ LIỆU TỪ INTENT
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

                // ===== ẢNH + NÚT BACK =====
                Box {

                    // ẢNH
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp),

                        contentScale = ContentScale.Crop
                    )

                    // OVERLAY
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Color.Black.copy(alpha = 0.2f)
                            )
                    )

                    // NÚT QUAY LẠI
                    IconButton(

                        onClick = {

                            (context as? ComponentActivity)
                                ?.finish()
                        },

                        modifier = Modifier
                            .padding(16.dp)
                            .background(
                                Color.Black.copy(alpha = 0.5f),
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
                }

                // ===== THÔNG TIN TOUR =====
                Column(

                    modifier = Modifier
                        .padding(20.dp)
                        .background(
                            Color.Black.copy(alpha = 0.45f),

                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(24.dp)

                ) {

                    // TÊN ĐỊA ĐIỂM
                    Text(
                        text = name,

                        style =
                            MaterialTheme
                                .typography
                                .headlineLarge,

                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // MÔ TẢ
                    Text(
                        text = desc,

                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge,

                        color = Color.White
                    )
                }
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

                    modifier = Modifier.weight(1f)
                ) {

                    Text("ĐẶT TOUR")
                }

                OutlinedButton(
                    onClick = {},

                    modifier = Modifier.weight(1f)
                ) {

                    Text("GỢI Ý")
                }
            }
        }
    }
}