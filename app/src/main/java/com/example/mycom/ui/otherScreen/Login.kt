package com.example.myapplication.ui.theme.otherScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
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
import com.example.mycom.data.ManagerList.manager
import com.example.mycom.ui.employee.EmployeeEvent
import com.example.mycom.ui.employee.EmployeeState
import com.example.mycom.ui.theme.MyComTheme
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    state: EmployeeState,
    onEvent: (EmployeeEvent) -> Unit,
    onFirstButtonClicked: (String, String) -> Unit,
    onSecondButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "LOGIN",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
            Divider(Modifier.padding(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier.padding(top = 64.dp)
            ) {
                // User ID
                TextField(
                    value = id,
                    label = { Text(text = "User ID") },
                    onValueChange = { id = it },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                // Password
                TextField(
                    label = { Text(text = "Password") },
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.padding(56.dp))

            //Login
            Button(
                onClick = {
                    val firstChar = id[0]
                    if(firstChar == 'M') {
                        onFirstButtonClicked(id, password)
                    } else if (firstChar == 'E') {
                        onEvent(EmployeeEvent.login(id,password))
                        onFirstButtonClicked(id, password)
                    }
                     },
                modifier = Modifier
                    .width(144.dp)
                    .padding(top = 32.dp)
            ) {
                Text(text = "LOGIN")
            }

            //Register
            OutlinedButton(
                onClick = onSecondButtonClicked,
                modifier = Modifier
                    .width(144.dp)
                    .padding(top = 16.dp)
            ) {
                Text(text = "SIGN UP")
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
fun LoginPreview() {
    MyComTheme {
        //LoginScreen(modifier = Modifier.fillMaxSize())
    }
}
