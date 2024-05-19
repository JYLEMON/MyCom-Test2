package com.example.mycom.ui.ManagementModule.RuleModify

import com.example.mycom.timeRangeData.TimeRange
import com.example.mycom.ui.ManagementModule.RuleModify.TimeRangeSortType

data class TimeRangeState(
    val timeNow: List<TimeRange> = emptyList(),
    val startHour: Int = 0,
    val startMinute: Int = 0,
    val endHour: Int = 0,
    val endMinute: Int = 0,
    val startAmPm: String = "",
    val endAmPm: String = "",
    val isSettingStartTime: Boolean = false,
    val isSettingEndTime: Boolean = false,
    val sortType: TimeRangeSortType = TimeRangeSortType.VERSION
)
