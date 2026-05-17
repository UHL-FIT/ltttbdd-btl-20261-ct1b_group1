package com.example.dattuadulich

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.jvm.java
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.History
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MainScreen()
        }
    }
}

data class Destination(
    val name: String,
    val imageRes: Int,      // Ảnh card địa danh
    val bannerRes: Int,     // Ảnh banner trượt
    val description: String
)
data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)
@Preview
@Composable
fun MainScreen() {

    val context = LocalContext.current

    val destinations = listOf(
        Destination(
            name = "Đà Nẵng",
            imageRes = R.drawable.danang,
            bannerRes = R.drawable.banner_danang,
            description = "Đà Nẵng là một trong những thành phố biển đẹp và đáng sống nhất Việt Nam, nổi bật với sự kết hợp hài hòa giữa thiên nhiên, con người và nhịp sống hiện đại.\n" +
                    "\n" +
                    "Nằm bên bờ biển miền Trung, Đà Nẵng sở hữu những bãi biển dài, cát trắng mịn và nước biển trong xanh như Bãi biển Mỹ Khê – từng được bình chọn là một trong những bãi biển đẹp nhất hành tinh. Không chỉ có biển, thành phố còn được bao quanh bởi núi non hùng vĩ như Ngũ Hành Sơn, tạo nên khung cảnh thiên nhiên rất đa dạng và cuốn hút.\n" +
                    "\n" +
                    "Đà Nẵng còn nổi tiếng với những cây cầu độc đáo bắc qua sông Hàn, đặc biệt là Cầu Rồng – biểu tượng của thành phố, có thể phun lửa và nước vào cuối tuần, thu hút rất nhiều du khách.\n" +
                    "\n" +
                    "Không chỉ đẹp về cảnh quan, Đà Nẵng còn gây ấn tượng bởi:\n" +
                    "\n" +
                    "\uD83C\uDF06 Thành phố sạch sẽ, hiện đại, giao thông thuận tiện\n" +
                    "\uD83D\uDE0A Con người thân thiện, hiếu khách\n" +
                    "\uD83C\uDF5C Ẩm thực phong phú với các món nổi tiếng như mì Quảng, bún chả cá\n" +
                    "\n" +
                    "Đặc biệt, Đà Nẵng còn là điểm trung chuyển lý tưởng để khám phá các địa danh nổi tiếng lân cận như Phố cổ Hội An hay Cố đô Huế."
        ),
        Destination(
            name = "Huế",
            imageRes = R.drawable.hue,
            bannerRes = R.drawable.banner_hue,
            description = "Huế là một thành phố mang vẻ đẹp trầm mặc và cổ kính, từng là kinh đô của triều đại nhà Nguyễn – triều đại phong kiến cuối cùng của Việt Nam. Nơi đây không ồn ào, náo nhiệt mà mang trong mình nét yên bình, sâu lắng rất riêng.\n" +
                    "\n" +
                    "Huế nổi bật với quần thể di tích lịch sử đồ sộ, tiêu biểu là Kinh thành Huế – nơi sinh sống và làm việc của các vị vua xưa. Với kiến trúc cung đình uy nghiêm, tường thành cổ kính và những cổng thành rêu phong, nơi đây như đưa du khách quay ngược thời gian.\n" +
                    "\n" +
                    "Không chỉ có cung điện, Huế còn nổi tiếng với hệ thống lăng tẩm độc đáo như Lăng Khải Định hay Lăng Minh Mạng, mỗi nơi mang một phong cách kiến trúc riêng, thể hiện rõ dấu ấn của từng vị vua.\n" +
                    "\n" +
                    "Bên cạnh đó, dòng Sông Hương thơ mộng chảy qua lòng thành phố, kết hợp với Chùa Thiên Mụ cổ kính tạo nên một khung cảnh rất đỗi nên thơ và thanh bình.\n" +
                    "\n" +
                    "Huế còn ghi dấu ấn với:\n" +
                    "\n" +
                    "\uD83C\uDF8E Văn hóa cung đình đặc sắc (nhã nhạc cung đình, lễ nghi truyền thống)\n" +
                    "\uD83C\uDF5C Ẩm thực tinh tế như bún bò Huế, bánh bèo, bánh nậm\n" +
                    "\uD83C\uDF27 Thời tiết đặc trưng với những cơn mưa nhẹ tạo nên nét buồn rất riêng"
        ),
        Destination(
            name = "Quảng Ninh",
            imageRes = R.drawable.quangninh,
            bannerRes = R.drawable.banner_quangninh,
            description = "Quảng Ninh là vùng đất nổi tiếng với kỳ quan thiên nhiên thế giới – Vịnh Hạ Long, một trong những điểm đến đẹp và ấn tượng nhất Việt Nam.\n" +
                    "\n" +
                    "Vịnh Hạ Long gây mê hoặc bởi hàng nghìn hòn đảo đá vôi lớn nhỏ nhô lên giữa làn nước xanh ngọc bích, tạo nên khung cảnh kỳ vĩ như tranh thủy mặc. Những hòn đảo với hình dáng độc đáo như hòn Gà Chọi, hòn Đỉnh Hương đã trở thành biểu tượng đặc trưng của vùng vịnh này.\n" +
                    "\n" +
                    "Không chỉ có cảnh quan hùng vĩ, Vịnh Hạ Long còn sở hữu hệ thống hang động kỳ bí và tuyệt đẹp như Hang Sửng Sốt hay Động Thiên Cung, với những khối thạch nhũ lung linh, hình thành qua hàng triệu năm.\n" +
                    "\n" +
                    "Đến Quảng Ninh, du khách còn có thể:\n" +
                    "\n" +
                    "\uD83D\uDEA4 Trải nghiệm du thuyền trên vịnh, ngắm hoàng hôn và bình minh tuyệt đẹp\n" +
                    "\uD83C\uDFDD Khám phá các bãi biển hoang sơ, đảo nhỏ yên bình\n" +
                    "\uD83C\uDF64 Thưởng thức hải sản tươi ngon như tôm, cua, mực\n" +
                    "\n" +
                    "Ngoài ra, khu vực Bãi Cháy với bãi biển dài và các khu vui chơi hiện đại cũng là điểm dừng chân hấp dẫn cho du khách."
        ),
        Destination(
            name = "Hà Nội",
            imageRes = R.drawable.hanoi,
            bannerRes = R.drawable.banner_hanoi,
            description = "Hà Nội là thủ đô nghìn năm văn hiến của Việt Nam, nơi hội tụ vẻ đẹp cổ kính, trầm mặc xen lẫn sự hiện đại và năng động của một đô thị lớn.\n" +
                    "\n" +
                    "Nhắc đến Hà Nội, người ta thường nghĩ ngay đến Hồ Hoàn Kiếm – trái tim của thành phố, nơi gắn liền với truyền thuyết lịch sử và không gian yên bình giữa lòng đô thị. Gần đó là Phố cổ Hà Nội với những con phố nhỏ, mái nhà cổ kính và nhịp sống buôn bán tấp nập, mang đậm dấu ấn thời gian.\n" +
                    "\n" +
                    "Hà Nội còn nổi bật với nhiều công trình văn hóa – lịch sử quan trọng như Văn Miếu - Quốc Tử Giám – trường đại học đầu tiên của Việt Nam, hay Lăng Chủ tịch Hồ Chí Minh – nơi lưu giữ thi hài vị lãnh tụ kính yêu.\n" +
                    "\n" +
                    "Không chỉ có lịch sử, Hà Nội còn hấp dẫn bởi:\n" +
                    "\n" +
                    "\uD83C\uDF38 Những mùa đặc trưng: mùa thu lá vàng, mùa hoa sữa thơm ngát\n" +
                    "\uD83C\uDF5C Ẩm thực nổi tiếng như phở, bún chả, bánh cuốn\n" +
                    "☕ Văn hóa cà phê vỉa hè rất riêng, chậm rãi và sâu lắng\n" +
                    "\n" +
                    "\uD83D\uDC49 Hà Nội không quá hào nhoáng, nhưng lại có sức hút đặc biệt — một vẻ đẹp vừa cổ kính, vừa lãng mạn, khiến bất cứ ai ghé thăm cũng dễ dàng lưu luyến và nhớ mãi."
        )
    )

    Box {

        // ✅ BACKGROUND ẢNH
        Image(
            painter = painterResource(id = R.drawable.background_app),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 1f
        )

        // ✅ Lớp overlay cho dễ nhìn chữ
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.25f))
        )

        // ✅ NỘI DUNG APP
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                BottomMenuBar(selectedIndex = 0)
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

                item {
                    TopSection()
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    AutoSlidingBanner(
                        banners = destinations,
                        onBannerClick = { destination ->
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("destination_name", destination.name)
                            intent.putExtra("destination_desc", destination.description)
                            intent.putExtra("destination_image", destination.bannerRes)
                            context.startActivity(intent)
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Text(
                        "Điểm đến nổi bật",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White // 👈 đổi màu cho dễ nhìn
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(destinations) { destination ->
                    DestinationCard(destination) {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("destination_name", destination.name)
                        intent.putExtra("destination_desc", destination.description)
                        intent.putExtra("destination_image", destination.bannerRes)
                        context.startActivity(intent)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
@Composable
fun TopSection() {

    var search by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = search,
            onValueChange = { search = it },
            placeholder = {
                Text(
                    "Tìm kiếm điểm đến...",
                    color = Color.White.copy(alpha = 0.7f)
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(30.dp), // 👈 bo tròn đẹp
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black.copy(alpha = 0.4f),
                unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = {},
            modifier = Modifier
                .background(
                    Color.Black.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(50)
                )
        ) {
            Icon(Icons.Default.Language, null, tint = Color.White)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingBanner(
    banners: List<Destination>,
    onBannerClick: (Destination) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { banners.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)

            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) { page ->

        val banner = banners[page]

        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onBannerClick(banner)
                },
            shape = RoundedCornerShape(20.dp)
        ) {

            Box {

                Image(
                    painter = painterResource(id = banner.bannerRes),
                    contentDescription = banner.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                )

            }
        }
    }
}
@Composable
fun BottomMenuBar(selectedIndex: Int) {

    val context = LocalContext.current

    val items = listOf(

        BottomNavItem("Home", Icons.Default.Home),

        BottomNavItem(
            "Khám phá",
            Icons.Default.Explore
        ),

        BottomNavItem(
            "Ưu đãi",
            Icons.Default.LocalOffer
        ),

        BottomNavItem(
            "Lịch sử",
            Icons.Default.History
        ),

        BottomNavItem(
            "Tài khoản",
            Icons.Default.Person
        )
    )

    NavigationBar(
        containerColor = Color(0xFFFFB74D)
    ) {

        items.forEachIndexed { index, item ->

            NavigationBarItem(

                selected = selectedIndex == index,

                onClick = {

                    when(index) {

                        0 -> {

                            if(selectedIndex != 0) {

                                context.startActivity(
                                    Intent(
                                        context,
                                        MainActivity::class.java
                                    )
                                )
                            }
                        }

                        1 -> {

                            if(selectedIndex != 1) {

                                context.startActivity(
                                    Intent(
                                        context,
                                        ExploreActivity::class.java
                                    )
                                )
                            }
                        }
                    }
                },

                icon = {

                    Icon(
                        item.icon,
                        contentDescription = item.title
                    )
                },

                label = {

                    Text(item.title)
                }
            )
        }
    }
}

@Composable
fun DestinationCard(
    destination: Destination,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onClick() },
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
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}