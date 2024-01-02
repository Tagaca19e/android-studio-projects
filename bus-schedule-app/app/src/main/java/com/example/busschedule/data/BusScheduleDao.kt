package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(schedule: BusSchedule)

  @Update
  suspend fun update(schedule: BusSchedule)

  @Delete
  suspend fun delete(schedule: BusSchedule)

  @Query("SELECT * FROM items WHERE id = :id")
  fun getSchedule(id: Int): kotlinx.coroutines.flow.Flow<BusSchedule>

  @Query("SELECT * FROM items ORDER BY arrivalTimeInMillis ASC")
  fun getAllSchedules(): Flow<List<BusSchedule>>

  @Query("SELECT * FROM items WHERE stopName = :stopName")
  fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}
