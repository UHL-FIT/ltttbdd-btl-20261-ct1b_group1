package com.example.dattuadulich

import android.app.Activity
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.jvm.java

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)
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
                                context.startActivity(Intent(context, MainActivity::class.java)
                                )

                                (context as? Activity)?.finish()
                            }
                        }

                        1 -> {
                            if(selectedIndex != 1) {
                                context.startActivity(Intent(context, ExploreActivity::class.java)
                                )

                                (context as? Activity)?.finish()
                            }
                        }
                        2 -> {
                            if(selectedIndex != 2) {
                                context.startActivity(Intent(context, OfferActivity::class.java)
                                )

                                (context as? Activity)?.finish()
                            }
                        }
                        3 -> {
                            if(selectedIndex != 3) {
                                context.startActivity(Intent(context, HistoryActivity::class.java)
                                )

                                (context as? Activity)?.finish()
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
