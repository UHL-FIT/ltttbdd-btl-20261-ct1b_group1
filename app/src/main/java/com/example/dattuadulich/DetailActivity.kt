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
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.graphics.Color
    
    class DetailActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
    
            // Nhận dữ liệu gửi từ Intent
            val name = intent.getStringExtra("destination_name") ?: "Không có tên"
            val desc = intent.getStringExtra("destination_desc") ?: "Không có mô tả"
            val image = intent.getIntExtra("destination_image", 0)
    
            setContent {
                MaterialTheme {
                    DetailScreen(name, desc, image)
                }
            }
        }
    }


    @Composable
    fun DetailScreen(name: String, desc: String, image: Int) {

        Box {

            // 🔹 BACKGROUND
            Image(
                painter = painterResource(id = R.drawable.background_app),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 1f
            )

            // 🔹 OVERLAY (giúp chữ dễ nhìn)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
            )

            // 🔹 NỘI DUNG CHÍNH
            Column(modifier = Modifier.fillMaxSize()) {

                // PHẦN CUỘN
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(
                                Color.Black.copy(alpha = 0.45f),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(50.dp)
                    ) {

                        Text(
                            text = name,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }

                // BUTTON
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("ĐẶT TOUR NGAY")
                    }

                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("GỢI Ý ĐẶT TOUR")
                    }
                }
            }
        }
    }