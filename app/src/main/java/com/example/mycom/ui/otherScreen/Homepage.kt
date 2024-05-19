package com.example.myapplication.ui.theme.otherScreen

import StatusNavigationHost
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.managementsystem.ManagementModule.WorkEvent
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState
import com.example.mycom.R
import com.example.mycom.ui.Approvalscreen.AddStaffApporval
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState
import com.example.mycom.ui.status.DisplayAssignableWorkListScreen
import com.example.mycom.ui.status.EmployeeWorkEvent
import com.example.mycom.ui.status.EmployeeWorkState
import com.example.mycom.ui.theme.MyComTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

enum class EmployeeScreen(@StringRes val title: Int) {
    Home(title = R.string.home),
    WorkSpace(title = R.string.WorkSpace),
    Application(title = R.string.Application),
    Status(title = R.string.Status),
    Profile(title = R.string.profile),
    Management(title = R.string.manageScreen)
}

@Composable
fun Homepage(
    onClickButton1: () -> Unit = {},
    onClickButton2: () -> Unit = {},
    onNavigateToWorkSpace: () -> Unit,
    onNavigateToApplication: () -> Unit,
    onNavigateToStatus: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    var punchInTime by remember { mutableStateOf<LocalTime?>(null) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 30.dp)
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "Liang You Xian", fontSize = 35.sp)
            Text(
                text = "E001",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Divider(Modifier.padding(bottom = 32.dp))

            // Date Display
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        RealDate()
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.padding(32.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        RealTimeClock(punchInTime)
                    }
                }
            }

            Spacer(modifier = Modifier.padding(32.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                OutlinedButton(
                    onClick = {
                        punchInTime = LocalTime.now()
                        onClickButton1()
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .height(180.dp)
                        .aspectRatio(1f)
                ) {
                    Text(
                        text = "Punch In",
                        fontSize = 25.sp
                    )
                }
            }
        }

        BottomAppBar(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            containerColor = Color.LightGray
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(onClick = onNavigateToWorkSpace) {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = "WorkSpace"
                    )
                }

                IconButton(onClick = onNavigateToApplication) {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = "Application"
                    )
                }

                IconButton(onClick = onNavigateToStatus) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "Status"
                    )
                }

                IconButton(onClick = onNavigateToProfile) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "Profile"
                    )
                }

                IconButton(onClick = onNavigateToHome) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "Management Screen"
                    )
                }
            }
        }
    }
}

@Composable
fun NormalEmployeeScreen(
    workState: WorkState,
    onWorkEvent: (WorkEvent) -> Unit,
    aprState: ApprovalState,
    onAprEvent: (ApprovalEvent) -> Unit,
    employeeWorkListState: EmployeeWorkState,
    employeeWorkEvent: (EmployeeWorkEvent) -> Unit,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = EmployeeScreen.valueOf(
        backStackEntry?.destination?.route ?: EmployeeScreen.Home.name
    )
    NavHost(navController = navController, startDestination = EmployeeScreen.Home.name) {
        composable(route = EmployeeScreen.Home.name) {
            Homepage(
                onNavigateToWorkSpace = {navController.navigate(EmployeeScreen.WorkSpace.name)},
                onNavigateToApplication = {navController.navigate(EmployeeScreen.Application.name)},
                onNavigateToStatus = {navController.navigate(EmployeeScreen.Status.name)},
                onNavigateToProfile = {navController.navigate(EmployeeScreen.Profile.name)},
                onNavigateToHome = {navController.navigate(EmployeeScreen.Home.name)}
            )
        }
        composable(route = EmployeeScreen.WorkSpace.name) {
            DisplayAssignableWorkListScreen(state = workState, onWorkSelected = {})
        }
        composable(route = EmployeeScreen.Application.name){
            AddStaffApporval(state = aprState, onEvent = onAprEvent)
        }
        composable(route = EmployeeScreen.Status.name) {
            StatusNavigationHost(
                workListState = workState,
                employeeWorkListState = employeeWorkListState,
                onEvent = employeeWorkEvent
            )
        }
        composable(route = EmployeeScreen.Profile.name) {
            ProfileScreen(name = "", empId = "", email = "", salary = "")
        }
    }
}

@Composable
fun RealTimeClock(punchInTime: LocalTime?) {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val formattedTime = punchInTime?.format(formatter) ?: "Not Punched In"

    Text(
        text = "Punch In Time: ",
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
    Text(
        text = formattedTime,
        fontSize = 20.sp,
        style = TextStyle(color = Color.DarkGray),
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun RealDate() {
    var currentDate by remember { mutableStateOf(Date()) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Update every second
            currentDate = Date()
        }
    }
    val pattern = "dd/MM/yyyy"
    val dateFormat = SimpleDateFormat(pattern)
    val formattedDate = dateFormat.format(currentDate)

    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Current Date",fontSize = 25.sp,)
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date",
                Modifier.padding(start = 16.dp)
            )
        }
        Text(
            text = formattedDate,
            fontSize = 25.sp,
            style = TextStyle(color = Color.DarkGray),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    MyComTheme {
    }
}
