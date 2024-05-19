package com.example.mycom.ui.ManagementModule.RuleModify

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycom.timeRangeData.TimeRange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimeRangeViewModel(
    private val dao: TimePickerDao
):ViewModel() {
    private val _sortType = MutableStateFlow(TimeRangeSortType.VERSION)
    private val _timeNow = _sortType
        .flatMapLatest {sortType ->
            when(sortType) {
                TimeRangeSortType.VERSION -> dao.getTimeRange()
            }
        }

    private val _state = MutableStateFlow(TimeRangeState())
    val state = combine(_state, _sortType, _timeNow) { state, sortType, timeNow ->
        state.copy(
            timeNow = timeNow,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TimeRangeState())

    val timeRangeList = listOf(
        TimeRange(
            startHour = 1,
            startMinute = 0,
            startAmPm = "AM",
            endHour = 6,
            endMinute = 0,
            endAmPm = "PM"
        ),
    )

    fun onEvent(event: TimePickerEvent) {
        when(event) {
            TimePickerEvent.HideStartTimeDialog -> {
                _state.update { it.copy(
                    isSettingStartTime = false
                ) }
            }
            TimePickerEvent.HideEndTimeDialog -> {
                _state.update {it.copy(
                  isSettingEndTime = false
                ) }
            }
            TimePickerEvent.ShowStartTimeDialog -> {
                _state.update { it.copy(
                    isSettingStartTime = true
                ) }
            }
            TimePickerEvent.ShowEndTimeDialog -> {
                _state.update { it.copy(
                    isSettingEndTime = true
                ) }
            }

            TimePickerEvent.SaveDefaultTime -> {
                _state.update {it.copy(
                    startHour = 1,
                    startMinute = 0,
                    startAmPm = "AM",
                    endHour = 6,
                    endMinute = 0,
                    endAmPm = "PM"
                )
                }

                viewModelScope.launch {
                    dao.upsertTimeRange(
                        timeRange = timeRangeList.first()
                    )
                }
            }

            TimePickerEvent.SaveStartTime -> {
                val currentTimeRange = state.value.timeNow.last()
                val startHour = state.value.startHour
                val startMinute = state.value.startMinute
                val endHour = currentTimeRange.endHour
                val endMinute = currentTimeRange.endMinute
                val startAmPm = state.value.startAmPm
                val endAmPm = currentTimeRange.endAmPm

                if(startHour == 0) {
                    return
                }

                val timeRange = TimeRange(
                    startHour = startHour,
                    startMinute = startMinute,
                    endHour = endHour,
                    endMinute = endMinute,
                    startAmPm = startAmPm,
                    endAmPm = endAmPm
                )

                viewModelScope.launch {
                    dao.upsertTimeRange(timeRange)
                    dao.deleteTimeRange(currentTimeRange)
                }
            }

            TimePickerEvent.SaveEndTime -> {
                val currentTimeRange = state.value.timeNow.last()
                val startHour = currentTimeRange.startHour
                val startMinute = currentTimeRange.startMinute
                val endHour = state.value.endHour
                val endMinute = state.value.endMinute
                val startAmPm = currentTimeRange.startAmPm
                val endAmPm = state.value.endAmPm

                if (endHour == 0) {
                    return
                }

                val timeRange = TimeRange(
                    startHour = startHour,
                    startMinute = startMinute,
                    endHour = endHour,
                    endMinute = endMinute,
                    startAmPm = startAmPm,
                    endAmPm = endAmPm
                )

                viewModelScope.launch {
                    dao.upsertTimeRange(timeRange)
                    dao.deleteTimeRange(currentTimeRange)
                }
            }

            is TimePickerEvent.SetStartHour -> {
                _state.update { it.copy(
                    startHour = event.startHour
                ) }
            }
            is TimePickerEvent.SetStartMinute -> {
                _state.update { it.copy(
                    startMinute = event.startMinute
                ) }
            }
            is TimePickerEvent.SetStartAmPm -> {
                _state.update { it.copy(
                    startAmPm = event.startAmPm
                ) }
            }
            is TimePickerEvent.SetEndHour -> {
                _state.update { it.copy(
                    endHour = event.endHour
                ) }
            }
            is TimePickerEvent.SetEndMinute -> {
                _state.update { it.copy(
                    endMinute = event.endMinute
                ) }
            }
            is TimePickerEvent.SetEndAmPm -> {
                _state.update { it.copy(
                    endAmPm = event.endAmPm
                ) }
            }
        }
    }
}