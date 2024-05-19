package com.example.myapplication.DatabaseAttendance

sealed interface AttendanceEvent {
    object SaveAttendance: AttendanceEvent
    data class Setstaffid(val staffid: String):AttendanceEvent
    data class SetPunchInTime(val punchintime: String):AttendanceEvent
    data class SetPunchOutTime(val punchinouttime: String):AttendanceEvent
    data class SetLateOrAttend(val lateorattend: String):AttendanceEvent
    data class SetAttendDate(val attenddate: String):AttendanceEvent
    object ShowAttendanceList:AttendanceEvent
    object HideAttendanceList:AttendanceEvent
    data class SortAttendance(val attendanceSortType: AttendanceSortType):AttendanceEvent
    data class DeleteAttendance(val attendance:Attendance):AttendanceEvent
}