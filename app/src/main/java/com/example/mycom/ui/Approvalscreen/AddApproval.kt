package com.example.myapplication.ui.theme.Approvalscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState


@Composable
fun AddStaffApprovalList (
    state: ApprovalState,
    onEvent: (ApprovalEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            onEvent(ApprovalEvent.Hidelist)
        },
        title = { Text(text = "Send a Approval") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //Staff ID
                TextField(
                    value = state.staffid,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetStaffApprovalList(it))
                    },
                    placeholder = {
                        Text(text = "Name")
                    }
                )
                //leave and late status
                TextField(
                    value = state.leaveandlate,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetLeaveandLate(it))
                    },
                    placeholder = {
                        Text(text = "Leave")
                    }
                )
                //Reason
                TextField(
                    value = state.appreason,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetAppReason(it))
                    },
                    placeholder = {
                        Text(text = "Reason")
                    }
                )
                //State Info
                TextField(
                    value = state.stateinfo,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetStateInfo(it))
                    },
                    placeholder = {
                        Text(text = "Info")
                    }
                )
                //Time
                TextField(
                    value = state.apptime,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetAppTime(it))
                    },
                    placeholder = {
                        Text(text = "Time")
                    }
                )
                //Date
                TextField(
                    value = state.appdate,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetAppDate(it))
                    },
                    placeholder = {
                        Text(text = "Date")
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(ApprovalEvent.SaveApproval)
                }
            ) {
                Text(text = "Send")
            }
        }
    )
}
