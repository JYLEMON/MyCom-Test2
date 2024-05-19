package com.example.myapplication.DatabaseAttendance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Attendance(
    val punchintime :String,
    val punchinouttime :String,
    val lateorattend :String,
    val attenddate :String,
    @PrimaryKey(autoGenerate = false)
    val staffid : String = "",
)
