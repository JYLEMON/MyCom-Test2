package com.example.myapplication.DatabaseAttendance

data class AttendanceState(
    val attendance: List<Attendance> = emptyList(),
    val punchintime :String = "",
    val punchinouttime :String = "",
    val lateorattend :String = "",
    val attenddate :String = "",
    val staffid : String = "",
    val isAddingAttendance: Boolean = false,
    val attendanceSortType: AttendanceSortType = AttendanceSortType.Attendance
)
