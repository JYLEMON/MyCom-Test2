package com.example.mycom.ui.ManagementModule.RuleModify

interface TimePickerEvent {
    object SaveStartTime: TimePickerEvent
    object SaveEndTime: TimePickerEvent
    object SaveDefaultTime: TimePickerEvent
    data class SetStartHour(val startHour: Int): TimePickerEvent
    data class SetStartMinute(val startMinute: Int): TimePickerEvent
    data class SetEndHour(val endHour: Int): TimePickerEvent
    data class SetEndMinute(val endMinute: Int): TimePickerEvent
    data class SetStartAmPm(val startAmPm: String): TimePickerEvent
    data class SetEndAmPm(val endAmPm: String): TimePickerEvent
    object ShowStartTimeDialog: TimePickerEvent
    object HideStartTimeDialog: TimePickerEvent
    object ShowEndTimeDialog: TimePickerEvent
    object HideEndTimeDialog: TimePickerEvent
}