package com.example.dattuadulich

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dattuadulich.navigation.AppNavigation
import com.example.dattuadulich.ui.theme.DattuadulichTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DattuadulichTheme {
                AppNavigation()
            }
        }
    }
}