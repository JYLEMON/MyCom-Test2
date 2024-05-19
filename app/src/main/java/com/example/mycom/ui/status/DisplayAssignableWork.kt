package com.example.mycom.ui.status

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.managementsystem.Data.Work
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState

@Composable
fun DisplayAssignableWorkListScreen(
    state: WorkState,
    onWorkSelected: (Work) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 48.dp)
        ) {
            items(state.workList) { work ->
                AssignableWorkListItem(
                    work = work,
                    onWorkSelected = onWorkSelected,
                )
            }
        }
    }
}

@Composable
fun AssignableWorkListItem(
    work: Work,
    onWorkSelected: (Work) -> Unit
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

    DisplayAssignableWorkListScreen(
        state = WorkState(),
        onWorkSelected = {},
    )
}