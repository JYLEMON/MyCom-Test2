package com.example.managementsystem.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycom.ui.ManagementModule.ManageWork.WorkDao

@Database(
    entities = [Work::class],
    version = 1
)
abstract class WorkDatabase: RoomDatabase() {
    abstract val dao: WorkDao
}