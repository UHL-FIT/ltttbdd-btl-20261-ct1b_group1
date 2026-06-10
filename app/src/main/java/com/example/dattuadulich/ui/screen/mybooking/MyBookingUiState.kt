package com.example.dattuadulich.ui.screen.mybooking

import com.example.dattuadulich.data.local.DatTourEntity

data class MyBookingUiState(
    val historyList: List<DatTourEntity> = emptyList(),
    val tongSoTour: Int = 0,
    val tongTien: Double = 0.0,
    val maxTour: DatTourEntity? = null,
    val minTour: DatTourEntity? = null
)
