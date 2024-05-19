package com.example.myapplication.DatabaseAttendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AttendanceViewModel(
    private val attedao:AttendanceDao): ViewModel(){

    private val _attendancesortType = MutableStateFlow(AttendanceSortType.Attendance)
    private val _attendance = _attendancesortType
        .flatMapLatest { attendanceSortType ->
            when(attendanceSortType){
                AttendanceSortType.Attendance -> attedao.getAttendanceList()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(AttendanceState())
    val state = combine(_state,_attendance,_attendancesortType){state,attendance,attendancesortType ->
        state.copy(
            attendance = attendance,
            attendanceSortType = attendancesortType


        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AttendanceState())

    fun onEvent(event: AttendanceEvent) {
        when (event) {
            is AttendanceEvent.DeleteAttendance -> {
                viewModelScope.launch {
                    attedao.deleteattendance(event.attendance)
                }
            }

            AttendanceEvent.HideAttendanceList -> {
                _state.update {
                    it.copy(
                        isAddingAttendance = false
                    )
                }
            }

            AttendanceEvent.SaveAttendance -> {
                val attenddate = state.value.attenddate
                val lateorattend = state.value.lateorattend
                val punchintime = state.value.punchintime
                val punchinouttime = state.value.punchinouttime
                val staffid = state.value.staffid

                if (attenddate.isBlank() || lateorattend.isBlank() || punchintime.isBlank() || punchinouttime.isBlank() || staffid.isBlank()) {
                    return
                }
                val attandence = Attendance(
                    attenddate = attenddate,
                    lateorattend = lateorattend,
                    punchintime = punchintime,
                    punchinouttime = punchinouttime,
                    staffid = staffid
                )
                viewModelScope.launch {
                    attedao.upsertAttendance(attandence)

                }
                _state.update {
                    it.copy(
                        isAddingAttendance = false,
                        attenddate = "",
                        lateorattend = "",
                        punchintime = "",
                        punchinouttime = "",
                        staffid = ""
                    )
                }
            }

            is AttendanceEvent.SetAttendDate -> {
                _state.update {
                    it.copy(
                        attenddate = event.attenddate
                    )
                }
            }

            is AttendanceEvent.SetLateOrAttend -> {
                _state.update {
                    it.copy(
                        lateorattend = event.lateorattend
                    )
                }
            }

            is AttendanceEvent.SetPunchInTime -> {
                _state.update {
                    it.copy(
                        punchintime = event.punchintime
                    )
                }
            }

            is AttendanceEvent.SetPunchOutTime -> {
                _state.update {
                    it.copy(
                        punchinouttime = event.punchinouttime
                    )
                }
            }

            is AttendanceEvent.Setstaffid -> {
                _state.update {
                    it.copy(
                        staffid = event.staffid
                    )
                }
            }

            AttendanceEvent.ShowAttendanceList -> {
                _state.update {
                    it.copy(
                        isAddingAttendance = true
                    )
                }
            }

            is AttendanceEvent.SortAttendance -> {
                _attendancesortType.value = event.attendanceSortType
            }
        }

    }
}