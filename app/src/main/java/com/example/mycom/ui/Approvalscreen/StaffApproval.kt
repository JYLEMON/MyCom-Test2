package com.example.myapplication.ui.theme.Approvalscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddApproval(
    state: ApprovalState,
    onEvent: (ApprovalEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            onEvent(ApprovalEvent.Hidelist)
        },
        title = { Text(text = "Add Approval") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.staffid,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetStaffApprovalList(it))
                    },
                    placeholder = {
                        Text(text = "Name")
                    }
                )

                TextField(
                    value = state.leaveandlate  ,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetLeaveandLate(it))
                    },
                    placeholder = {
                        Text(text = "Email")
                    }
                )
                TextField(
                    value = state.stateinfo ,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetStateInfo(it))
                    },
                    placeholder = {
                        Text(text = "State")
                    }
                )
                TextField(
                    value = state.appreason  ,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetAppReason(it))
                    },
                    placeholder = {
                        Text(text = "Reason")
                    }
                )
                TextField(
                    value = state.apptime  ,
                    onValueChange = {
                        onEvent(ApprovalEvent.SetAppTime(it))
                    },
                    placeholder = {
                        Text(text = "Time")
                    }
                )
                TextField(
                    value = state.appdate  ,
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
                Text(text = "Save")
            }
        }
    )
}
