package com.example.mycom.ui.ManagementModule.RuleModify

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.mycom.timeRangeData.TimeRange
import kotlinx.coroutines.flow.Flow

@Dao
interface TimePickerDao {
    @Upsert
    suspend fun upsertTimeRange(timeRange: TimeRange)

    @Delete
    suspend fun deleteTimeRange(timeRange: TimeRange)

    @Query("SELECT * FROM TimeRange ORDER BY version ASC")
    fun getTimeRange(): Flow<List<TimeRange>>
}