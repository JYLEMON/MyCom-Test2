package com.example.managementsystem.ManagementModule

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycom.R

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    onClickSetTime: () -> Unit,
    onClickEmp: () -> Unit,
    onClickApr: () -> Unit,
    onClickWork: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column (Modifier.fillMaxSize()) {
            Text(
                text = "Management Screen",
                Modifier.padding(8.dp),
                fontSize = 35.sp,
            )
            Divider(Modifier.padding(start = 8.dp, end = 8.dp))
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                NavigationCard("Set Work Time", onClick = onClickSetTime)
                NavigationCard("Employee Management", onClick = onClickEmp)
                NavigationCard("Approval Management", onClick = onClickApr)
                NavigationCard("Work Management", onClick = onClickWork)
            }
        }

    }

}

@Composable
fun NavigationCard(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun ManagementMainPreview() {
    val navController = rememberNavController()
    //MainScreen(navController)
}
