package com.example.managementsystem.ManagementModule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState

@Composable
fun AddWorkDialog (
    state: WorkState,
    onEvent: (WorkEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            onEvent(WorkEvent.HideDialog)
        },
        title = { Text(text = "Add Work") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.workTitle,
                    onValueChange = {
                        onEvent(WorkEvent.SetTitle(it))
                    },
                    placeholder = {
                        Text(text = "Title")
                    }
                )

                TextField(
                    value = state.workDescription  ,
                    onValueChange = {
                        onEvent(WorkEvent.SetDescription(it))
                    },
                    placeholder = {
                        Text(text = "Description")
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(WorkEvent.SaveWork)
                }
            ) {
                Text(text = "Save")
            }
        }
    )
}