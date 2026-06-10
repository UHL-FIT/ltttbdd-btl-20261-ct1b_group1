//trung gian room <> viewmodel
package com.example.dattuadulich.repository

import com.example.dattuadulich.data.local.BookingDao
import com.example.dattuadulich.data.local.DatTourEntity

class BookingRepository(private val bookingDao: BookingDao) {
    fun getAllBookings() = bookingDao.getAllBookings()
    suspend fun insertBooking(booking: DatTourEntity) = bookingDao.insertBooking(booking)
    suspend fun updateBooking(booking: DatTourEntity) = bookingDao.updateBooking(booking)
    suspend fun deleteBooking(booking: DatTourEntity) = bookingDao.deleteBooking(booking)
}
