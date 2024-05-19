package com.example.mycom.timeRangeData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycom.ui.ManagementModule.RuleModify.TimePickerDao

@Database(
    entities = [TimeRange::class],
    version = 1
)
abstract class TimeRangeDatabase: RoomDatabase() {

    abstract val dao: TimePickerDao
}