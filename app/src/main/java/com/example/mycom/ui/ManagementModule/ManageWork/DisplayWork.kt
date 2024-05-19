package com.example.managementsystem.ManagementModule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.managementsystem.Data.Work
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState

@Composable
fun DisplayWorkListScreen(
    state: WorkState,
    onNextButtonPress: () -> Unit,
    onWorkSelected: (Work) -> Unit,
    onDeleteWork: (Work) -> Unit,
    modifier: Modifier = Modifier
) {
    var workToDelete by remember { mutableStateOf<Work?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 48.dp)
        ) {
            items(state.workList) { work ->
                WorkListItem(
                    work = work,
                    onWorkSelected = onWorkSelected,
                    onDeleteWork = { workToDelete = work }
                )
            }
        }
        FloatingActionButton(
            onClick = onNextButtonPress,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Employee",
            )
        }

        // Background overlay
        if (workToDelete != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(32.dp)
                    .background(Color.Black.copy(alpha = 0.5f)) // Semi-transparent black color
                    .clickable { workToDelete = null }, // Dismiss the dialog on background click
                contentAlignment = Alignment.Center
            ) {
                // Delete confirmation dialog
                AlertDialog(
                    onDismissRequest = { workToDelete = null },
                    title = { Text(text = "Confirm Deletion") },
                    text = { Text(text = "Are you sure you want to delete ${workToDelete?.workTitle}?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                onDeleteWork(workToDelete!!)
                                workToDelete = null
                            }
                        ) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = { workToDelete = null }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WorkListItem(
    work: Work,
    onWorkSelected: (Work) -> Unit,
    onDeleteWork: () -> Unit // Callback for delete action
) {
    Spacer(modifier = Modifier.padding(4.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent,
            onClick = { onWorkSelected(work) }
            // Invoke the callback when card is clicked
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = work.workID)
                    Text(text = work.workTitle) // Convert id to string for display
                }
                IconButton(
                    onClick = onDeleteWork,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Employee"
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DisplayWorkListPreview() {

    DisplayWorkListScreen(
        state = WorkState(),
        onNextButtonPress = { /*TODO*/ },
        onWorkSelected = {},
        onDeleteWork = {}
    )
}