package com.example.dattuadulich.ui.screen.detail

data class DetailUiState(
    val isLoading: Boolean = true,
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val price: String = "Liên hệ",
    val rating: Double = 4.9
)