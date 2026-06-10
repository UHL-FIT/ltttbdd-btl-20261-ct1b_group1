package com.example.dattuadulich.ui.screen.booking

data class BookingUiState(
    val giaTien: Double = 0.0,
    val anhDiaDiem: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class TourInfo(
    val giaTien: Double,
    val anhDiaDiem: String
)