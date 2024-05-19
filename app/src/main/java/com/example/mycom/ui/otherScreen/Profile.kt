package com.example.myapplication.ui.theme.otherScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycom.ui.theme.MyComTheme

@Composable
fun ProfileScreen(
    name: String,
    empId: String,
    email: String,
    salary: String,
    onClickButton2: () -> Unit = {},
    onClickButton3: () -> Unit = {},
    onEditClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
            ) {
                Column {
                    Text(
                        text = name,
                        fontSize = 40.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Text(
                        text = empId,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Card(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    Modifier.padding(8.dp)
                ) {
                    Text(text = "Email:", fontSize = 20.sp)
                    Row {
                        Text(
                            text = email,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    Divider(Modifier.padding(bottom = 8.dp))

                    Text(text = "Salary:", fontSize = 20.sp)
                    Row {
                        Text(
                            text = "RM ",
                            fontSize = 25.sp,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = salary,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                    Divider(Modifier.padding(bottom = 8.dp))
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onClickButton2() }
            ) {
                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Application",
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Application"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = onClickButton3,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp
                    )
            }
        }

        FloatingActionButton(
            onClick = onEditClick,
            shape = CircleShape,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit, // Use the Edit icon
                contentDescription = "Edit"
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun profilePreview() {
    MyComTheme {
        ProfileScreen(
            name = "Liang You Xian",
            empId = "E001",
            email = "liangyouxian1@gmail.com",
            salary = "2000.50",
        )
    }
}
