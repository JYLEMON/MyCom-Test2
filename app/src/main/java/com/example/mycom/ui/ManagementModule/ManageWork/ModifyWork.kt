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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModifyWorkScreen(
    initialTitleField: String,
    initialDescriptionField: String,
    initialEmailField: String,
    onWorkUpdated: (String, String, String) -> Unit
){
    var title by remember { mutableStateOf(initialTitleField) }
    var description by remember { mutableStateOf(initialDescriptionField) }
    var email by remember { mutableStateOf(initialEmailField) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = "Work Title:",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = "Description:",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = "Contact Email:",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = {
                // Update the employee details and notify the changes back
                onWorkUpdated(title, description, email)
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Update Work")
        }
    }
}

@Preview
@Composable
fun ModifyWorkPreview() {
    ModifyWorkScreen(
        initialTitleField = "test document 1",
        initialDescriptionField = "this is just an example",
        initialEmailField = "example@gmail.com",
        onWorkUpdated = { _, _, _ -> /* Dummy lambda for preview */ }
    )
}