// EditEmployeeDialog.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mycom.data.Employee
import com.example.mycom.ui.employee.EmployeeEvent
import com.example.mycom.ui.employee.EmployeeState

@Composable
fun ShowEditDialog(
    state: EmployeeState,
    onEvent: (EmployeeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var editedEmployeeName by remember { mutableStateOf(state.selectedEmployee?.empName ?: "") }
    var editedEmployeeEmail by remember { mutableStateOf(state.selectedEmployee?.email ?: "") }
    var editedEmployeePassword by remember { mutableStateOf(state.selectedEmployee?.password ?: "") }
    var editedEmployeeSalary by remember { mutableStateOf(state.selectedEmployee?.salary ?: "0.0") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = {
                onEvent(EmployeeEvent.HideEditDialog)
            }),
        contentAlignment = Alignment.Center
    ) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                onEvent(EmployeeEvent.HideEditDialog)
            },
            title = { Text(text = "Edit Employee Details") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = editedEmployeeName,
                        onValueChange = { editedEmployeeName = it },
                        label = { Text("Name") }
                    )
                    TextField(
                        value = editedEmployeeEmail,
                        onValueChange = { editedEmployeeEmail = it },
                        label = { Text("Email") }
                    )
                    TextField(
                        value = editedEmployeePassword,
                        onValueChange = { editedEmployeePassword = it },
                        label = { Text("Password") }
                    )
                    TextField(
                        value = editedEmployeeSalary,
                        onValueChange = { newValue ->
                            editedEmployeeSalary = newValue.takeIf { it.toDoubleOrNull() != null } ?: editedEmployeeSalary
                        },
                        label = { Text("Salary") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (state.selectedEmployee != null) {
                            onEvent(
                                EmployeeEvent.UpdateEmployee(
                                    empName = editedEmployeeName,
                                    email = editedEmployeeEmail,
                                    password = editedEmployeePassword,
                                    salary = editedEmployeeSalary
                                )
                            )
                        }
                        onEvent(EmployeeEvent.HideEditDialog)
                    }
                ) {
                    Text(text = "Save")
                }
            }
        )
    }
}
