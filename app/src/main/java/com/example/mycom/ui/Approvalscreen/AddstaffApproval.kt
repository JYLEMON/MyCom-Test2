package com.example.mycom.ui.Approvalscreen


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState
import com.example.myapplication.ui.theme.Approvalscreen.StaffApprovalScreen

@Suppress("UNUSED_EXPRESSION")
@Composable
fun AddStaffApporval(
    state: ApprovalState,
    onEvent: (ApprovalEvent) -> Unit,
    onClickButton1: () -> Unit = {},
    modifier: Modifier =Modifier
) {
    var isLate by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val name :String = "姓名: 张三"
        val id :String = " 123456"
        // Display Name and ID
        Text(text = "Name:$name")
        Text(text = "ID:$id")
        onEvent(ApprovalEvent.SetStaffApprovalList(id))
        // Toggle switches for Late and Leave
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = if (isLate) " Late " else "Leave", fontSize = 18.sp)
            if((isLate)){
                onEvent(ApprovalEvent.SetLeaveandLate("Late"))
            }
            else
            {
                onEvent(ApprovalEvent.SetLeaveandLate("Leave"))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = isLate,
                onCheckedChange = { isLate = it }
            )
        }

        // Date and Time pickers (Placeholder for simplicity)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "DATE (格式: YYYY-MM-DD)")
        BasicTextField(
            value = state.appdate,
            onValueChange = {onEvent(ApprovalEvent.SetAppDate(it))},
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "选择时间 (格式: HH:MM)")
        BasicTextField(
            value = state.apptime,
            onValueChange = {  onEvent(ApprovalEvent.SetAppTime(it))},
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        )

        // Explanation field
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "提供解释")
        BasicTextField(
            value = state.appreason,
            onValueChange = {onEvent(ApprovalEvent.SetAppReason(it))},
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        )

        // Submit and Cancel buttons
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onClickButton1) {
                Text(text = "取消")
            }
            Button(onClick = {
                onEvent(ApprovalEvent.SetStateInfo("Requesting"))
                onEvent(ApprovalEvent.SaveApproval)
            }) {
                Text(text = "上交")
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AddStaffApporvalPreview() {
    AddStaffApporval(state = ApprovalState(), onEvent = {})

}