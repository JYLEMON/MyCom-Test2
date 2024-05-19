package com.example.managementsystem.ManagementModule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class WorkDetail(val title: String, val id: String, val description: String, val email: String)

@Composable
fun WorkDetailScreen(
    workDetail: WorkDetail,
    onEditClick: () -> Unit, // Callback for handling edit button click
    onWorkDetailsChange: (WorkDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .padding(vertical = 8.dp, horizontal = 8.dp)
        .fillMaxSize()
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Name:",
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 18.sp // Increase font size
                )
                Card(
                    modifier = Modifier.fillMaxWidth(), // Make the card fill the width

                ) {
                    Text(
                        text = "${workDetail.title}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp // Increase font size
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "ID:",
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 18.sp // Increase font size
                )
                Spacer(modifier = Modifier.width(30.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(), // Make the card fill the width

                ) {
                    Text(
                        text = "${workDetail.id}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp // Increase font size
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Description: ",
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 18.sp // Increase font size
                )
                Card(
                    modifier = Modifier.fillMaxWidth(), // Make the card fill the width

                ) {
                    Text(
                        text = "${workDetail.description}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp // Increase font size
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Email: ",
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 18.sp // Increase font size
                )
                Card(
                    modifier = Modifier.fillMaxWidth(), // Make the card fill the width

                ) {
                    Text(
                        text = "${workDetail.email}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp // Increase font size
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = onEditClick,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit, // Use the Edit icon
                contentDescription = "Edit"
            )
        }
    }
}