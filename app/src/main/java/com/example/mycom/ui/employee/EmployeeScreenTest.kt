package com.example.mycom.ui.employee

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.mycom.data.Employee

@Composable
fun EmployeeScreenTest(
    state: EmployeeState,
    onEvent: (EmployeeEvent) -> Unit
) {
    var deleteEmployee by remember { mutableStateOf<Employee?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(EmployeeEvent.ShowDialog)
                },
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Employee"
                )
            }
        }
    ) { padding ->
        if (state.isAddingEmployee) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                AddEmployeeDialog(state = state, onEvent = onEvent, modifier = Modifier.zIndex(2f))
            }
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.employee) { employee ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent,
                        onClick = {
                            onEvent(EmployeeEvent.ShowDetailDialog(employee))
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "${employee.empId}",
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "${employee.empName}",
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "${employee.email}",
                                    fontSize = 12.sp
                                )
                            }
                            IconButton(onClick = {
                                deleteEmployee = employee
                                onEvent(EmployeeEvent.ShowDeleteDialog)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Employee"
                                )
                            }
                        }
                    }
                }
            }
        }

        if (state.isShowingDetail) {
            ShowDetailDialog(state = state, onEvent = onEvent)
        }

        if (state.isDeletingEmployee) {
            DeleteEmployeeDialog(
                state = state,
                onEvent = onEvent,
                deleteEmployee = (deleteEmployee!!),
                onDeleteEmployee = {
                    deleteEmployee = null
                }
                )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun EmployeeTestPreview() {
    EmployeeScreenTest(state = EmployeeState()) {}
}
