package com.example.myapplication.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.DatabaseAttendance.Attendance

@Database(
    entities = [Approval::class],
    version = 1
)


abstract  class ApprovalDatabase : RoomDatabase(){
    abstract val apprdao: ApprovalStaffDao
}