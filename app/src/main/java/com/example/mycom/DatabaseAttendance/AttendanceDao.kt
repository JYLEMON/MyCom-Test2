package com.example.myapplication.DatabaseAttendance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {

    @Upsert
    fun upsertAttendance(attendance: Attendance)

    @Delete
    fun deleteattendance(attendance: Attendance)

    @Query("SELECT * FROM attendance ORDER BY staffid ASC")
    fun getAttendanceList(): Flow<List<Attendance>>
}