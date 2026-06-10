// nơi viết query database
package com.example.dattuadulich.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: DatTourEntity)

    @Query("SELECT * FROM dat_tour ORDER BY ngayDat DESC")
    fun getAllBookings(): Flow<List<DatTourEntity>>

    @Update
    suspend fun updateBooking(booking: DatTourEntity)

    @Delete
    suspend fun deleteBooking(booking: DatTourEntity)
}
