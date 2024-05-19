package com.example.mycom.EmployeeWorkData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.managementsystem.ManagementModule.EmployeeWorkDao
import com.example.mycom.ui.employee.EmployeeDao

@Database(
    entities = [EmployeeWork::class],
    version = 1
)
abstract class EmployeeWorkDatabase: RoomDatabase() {

    abstract val dao: EmployeeWorkDao
}