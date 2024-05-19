package com.example.managementsystem.ManagementModule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mycom.ui.ManagementModule.RuleModify.TimePickerEvent
import com.example.mycom.ui.ManagementModule.RuleModify.TimeRangeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowRulesScreen(
    state: TimeRangeState,
    onEvent: (TimePickerEvent) -> Unit
) {
    var startHourString by remember {
        mutableStateOf(state.timeNow.last().startHour.toString().padStart(2,'0'))
    }
    var endHourString by remember {
        mutableStateOf(state.timeNow.last().endHour.toString().padStart(2,'0'))
    }
    var startMinuteString by remember {
        mutableStateOf(state.timeNow.last().startMinute.toString().padStart(2,'0'))
    }
    var endMinuteString by remember {
        mutableStateOf(state.timeNow.last().endMinute.toString().padStart(2,'0'))
    }
    var startTime by remember {
        mutableStateOf("$startHourString:$startMinuteString${state.timeNow.last().startAmPm}")
    }
    var endTime by remember {
        mutableStateOf("$endHourString:$endMinuteString${state.timeNow.last().endAmPm}")
    }

    val timePickerState = rememberTimePickerState()

    var progress by remember { mutableStateOf(0) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Set Working Time",
                Modifier.padding(16.dp),
                fontSize = 24.sp
            )
        }

        Divider(Modifier.padding(8.dp))

        Card (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Current Start Time",
                        Modifier.padding(8.dp)
                    )
                    Text("$startTime", Modifier.padding(8.dp))
                }
                IconButton(onClick = {
                    showStartTimePicker = true
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Set Start Time"
                    )
                }
            }
        }

        Card (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Current End Time",
                        Modifier.padding(8.dp)
                    )
                    Text("$endTime", Modifier.padding(8.dp))
                }
                IconButton(onClick = {
                    showEndTimePicker = true
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Set End Time"
                    )
                }
            }
        }
    }

    if (showStartTimePicker) {
        TimePickerDialog(
            onDismissRequest = {
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        var hour = timePickerState.hour
                        val minute = timePickerState.minute
                        var am_pm = ""
                        onEvent(TimePickerEvent.SetStartMinute(minute))
                        when {
                            hour == 0 -> {
                                hour = 12
                                am_pm = "AM"
                            }
                            hour == 12 -> {
                                am_pm = "PM"
                            }
                            hour > 12 -> {
                                hour -= 12
                                am_pm = "PM"
                            }
                            else -> {
                                am_pm = "AM"
                            }
                        }
                        onEvent(TimePickerEvent.SetStartHour(hour))
                        onEvent(TimePickerEvent.SetStartAmPm(am_pm))
                        startHourString = hour.toString().padStart(2,'0')
                        startMinuteString = minute.toString().padStart(2,'0')
                        startTime = "$startHourString:$startMinuteString$am_pm"
                        onEvent(TimePickerEvent.SaveStartTime)
                        showStartTimePicker =false
                    }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showEndTimePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            TimePicker(
                state = timePickerState
            )
        }
    }
    if(showEndTimePicker) {
        TimePickerDialog(
            onDismissRequest = {
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        var hour = timePickerState.hour
                        val minute = timePickerState.minute
                        var am_pm = ""
                        onEvent(TimePickerEvent.SetEndMinute(minute))
                        when {
                            hour == 0 -> {
                                hour = 12
                                am_pm = "AM"
                            }
                            hour == 12 -> {
                                am_pm = "PM"
                            }
                            hour > 12 -> {
                                hour -= 12
                                am_pm = "PM"
                            }
                            else -> {
                                am_pm = "AM"
                            }
                        }
                        onEvent(TimePickerEvent.SetEndHour(hour))
                        onEvent(TimePickerEvent.SetEndAmPm(am_pm))
                        endHourString = hour.toString().padStart(2,'0')
                        endMinuteString = minute.toString().padStart(2,'0')
                        endTime = "$endHourString:$endMinuteString$am_pm"
                        onEvent(TimePickerEvent.SaveEndTime)
                        showEndTimePicker = false
                    }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showEndTimePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            TimePicker(
                state = timePickerState
            )
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RulesModifyPreview() {
}