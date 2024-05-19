package com.example.mycom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycom.ui.employee.EmployeeDao

@Database(
    entities = [Employee::class],
    version = 1
)
abstract class EmployeeDatabase: RoomDatabase() {

    abstract val dao: EmployeeDao
}