package com.example.myapplication.DatabaseAttendance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Attendance::class],
    version = 1
)

abstract class AttendanceDatabase: RoomDatabase() {
    abstract val attdao: AttendanceDao
}