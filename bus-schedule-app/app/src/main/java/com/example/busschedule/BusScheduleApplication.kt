package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.ScheduleDatabase

class BusScheduleApplication: Application() {
  val database: ScheduleDatabase by lazy { ScheduleDatabase.getDatabase(this) }
}