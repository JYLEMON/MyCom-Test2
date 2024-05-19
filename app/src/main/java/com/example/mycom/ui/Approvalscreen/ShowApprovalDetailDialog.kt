package com.example.mycom.ui.Approvalscreen

import com.example.mycom.ui.employee.EmployeeEvent
import com.example.mycom.ui.employee.EmployeeState


import ShowEditDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState

@Composable
fun ShowDetailDialog(
    state: ApprovalState,
    onEvent: (ApprovalEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedApproval = state.selectedApproval ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = { onEvent(ApprovalEvent.HideDetailDialog) }),
        contentAlignment = Alignment.Center
    ) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onEvent(ApprovalEvent.HideDetailDialog) },
            title = { Text(text = "Application List") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "ID: ${selectedApproval.staffid}")
                    Text(text = "Name: ${selectedApproval.name}")
                    Text(text = "Request: ${selectedApproval.leaveandlate}")
                    Text(text = "Time: ${selectedApproval.apptime}")
                    Text(text = "Date: ${selectedApproval.appdate}")
                    Text(text = "Reason: ${selectedApproval.appreason}")

                }
            },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {onEvent(ApprovalEvent.ApproveApproval(selectedApproval)) }
                    ) {
                        Text(text = "Approve")
                    }
                    Button(
                        onClick = { onEvent(ApprovalEvent.HideDetailDialog) }
                    ) {
                        Text(text = "Close")
                    }
                }
            }
        )
    }
}
