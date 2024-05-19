package com.example.mycom.ui.Approvalscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState

@Composable
fun ManagerApprovalScreen(
    state: ApprovalState,
    onClickButton1: () -> Unit = {},
    onEvent: (ApprovalEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        val info = "Requesting"
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.approval) { Approval ->
                if (Approval.stateinfo == info) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { onEvent(ApprovalEvent.SelectApproval(Approval)) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)

                        ) {
                            Text(
                                text = "${Approval.staffid}",
                                fontSize = 20.sp
                            )
                            Text(
                                text = "${Approval.name}",
                                fontSize = 20.sp
                            )

                            Text(
                                text = "${Approval.leaveandlate}",
                                fontSize = 12.sp)
                        }
                        IconButton(onClick = {
                            onEvent(ApprovalEvent.ApproveApproval(Approval))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Approve Request"
                            )
                        }
                        IconButton(onClick = {
                            onEvent(ApprovalEvent.RejectApproval(Approval))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Approve Request"
                            )
                        }
                    }
                }
            }
        }

    }
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun StaffApprovalPreview() {
    ManagerApprovalScreen(state = ApprovalState()){}

}