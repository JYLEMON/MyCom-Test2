package com.example.myapplication.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Approval(
    val appreason: String? ="",
    val leaveandlate : String? ="",
    val stateinfo: String? ="",
    val apptime:String? ="",
    val appdate:String? ="",
    val staffid: String ="None",
    val name: String ="None",
    @PrimaryKey(autoGenerate = true)
    val apporvalnum: Int = 0

)

