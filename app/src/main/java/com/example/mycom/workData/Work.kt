package com.example.managementsystem.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Work(
    val workTitle: String,
    val workDescription: String,
    val contactEmail: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val workID: String = "W$id"
)
