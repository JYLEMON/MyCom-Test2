package com.example.myapplication.ui.theme.otherScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycom.ui.employee.EmployeeEvent
import com.example.mycom.ui.employee.EmployeeState
import com.example.mycom.ui.theme.MyComTheme
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    state: EmployeeState,
    onEvent: (EmployeeEvent) -> Unit,
    onClickButton1: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf(state.password) }
    var confirmPassword by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "STAFF REGISTER",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
            Divider(Modifier.padding(horizontal = 16.dp))

            Text(
                text = "ID: E${(state.count + 1).toString().padStart(3, '0')}", // Display the ID
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                // Name
                TextField(
                    value = state.empName,
                    label = { Text(text = "User Name") },
                    onValueChange = { onEvent(EmployeeEvent.SetName(it)) },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                // Email
                TextField(
                    label = { Text(text = "Email") },
                    value = state.email,
                    onValueChange = { onEvent(EmployeeEvent.SetEmail(it)) },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                // Password
                TextField(
                    label = { Text(text = "Password") },
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                // Confirm Password
                TextField(
                    label = { Text(text = "Confirm Password") },
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }

            Button(
                onClick = {
                    if (password == confirmPassword) {
                        onEvent(EmployeeEvent.SetPassword(confirmPassword))
                        onEvent(EmployeeEvent.SetSalary(2500.00))
                        onEvent(EmployeeEvent.SaveEmployee)
                        password = ""
                        confirmPassword = ""
                        // Optionally clear the state or navigate away
                        onClickButton1()
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Register Failed : Passwords do not match")
                        }
                        password = ""
                        confirmPassword = ""
                    }
                },
                modifier = Modifier
                    .width(144.dp)
                    .padding(top = 32.dp)
            ) {
                Text(text = "REGISTER")
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun RegisterPreview() {
    MyComTheme {
        RegisterScreen(state = EmployeeState(), onEvent = {})
    }
}
