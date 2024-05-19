package com.example.managementsystem.ManagementModule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState

@Composable
fun AddWorkScreen(
    state: WorkState,
    onEvent: (WorkEvent) -> Unit,
    onAddButtonClick: () -> Unit
) {
    val nextId = remember {
        mutableStateOf(
            calculateNextId(state.workList.map { it.workID.toString() })
        )
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "ID : W${nextId.value.toString().padStart(3, '0')}",
                fontSize = 18.sp
            ) // Display the next available ID
        }

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Title:")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = state.workTitle,
                onValueChange = {
                    onEvent(WorkEvent.SetTitle(it))
                },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Description:")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = state.workDescription,
                onValueChange = {
                    onEvent(WorkEvent.SetDescription(it))
                },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Handler Email:")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = state.handlerEmail,
                onValueChange = {
                    onEvent(WorkEvent.SetEmail(it))
                },
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = {
                onAddButtonClick()
                onEvent(WorkEvent.SaveWork)
                // Potentially trigger UI update in EmployeeListScreen (explained later)
                nextId.value++ // Increment the next available ID for the next employee
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Add Employee")
        }
    }
}

fun calculateNextId(usedIds: List<String>): Int {
    val maxId = usedIds.mapNotNull { it.removePrefix("W").toIntOrNull() }.maxOrNull() ?: 0
    return maxId + 1
}