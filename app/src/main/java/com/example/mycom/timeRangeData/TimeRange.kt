package com.example.mycom.timeRangeData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimeRange(
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute:Int,
    val startAmPm: String,
    val endAmPm: String,
    @PrimaryKey(autoGenerate = true)
    val version: Int = 0
)
