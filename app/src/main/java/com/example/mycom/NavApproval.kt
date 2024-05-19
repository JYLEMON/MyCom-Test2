@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.managementsystem.ManagementModule.ManagementApp
import com.example.managementsystem.ManagementModule.WorkEvent
import com.example.myapplication.DatabaseApproval.ApprovalEvent
import com.example.myapplication.DatabaseApproval.ApprovalState
import com.example.myapplication.ui.theme.Approvalscreen.StaffApprovalScreen
import com.example.myapplication.ui.theme.otherScreen.Homepage
import com.example.myapplication.ui.theme.otherScreen.LoginScreen
import com.example.myapplication.ui.theme.otherScreen.NormalEmployeeScreen
import com.example.myapplication.ui.theme.otherScreen.ProfileScreen
import com.example.myapplication.ui.theme.otherScreen.RegisterScreen
import com.example.mycom.R
import com.example.mycom.data.ManagerList
import com.example.mycom.ui.Approvalscreen.AddStaffApporval
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState
import com.example.mycom.ui.ManagementModule.RuleModify.TimePickerEvent
import com.example.mycom.ui.ManagementModule.RuleModify.TimeRangeState
import com.example.mycom.ui.employee.EmployeeEvent
import com.example.mycom.ui.employee.EmployeeState
import com.example.mycom.ui.status.EmployeeWorkEvent
import com.example.mycom.ui.status.EmployeeWorkState

enum class MainScreen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Home(title = R.string.home),
    StaffProfile(title = R.string.profile),
    Register(title = R.string.register ),
    StaffApproval(title = R.string.staff),
    AddStaffApproval(title = R.string.AddApproval),
    ManagementHome(title = R.string.manageScreen)
}

@Composable
fun app(
    navController: NavHostController = rememberNavController(),
    state: EmployeeState,
    onEvent: (EmployeeEvent) -> Unit,
    workState: WorkState,
    onWorkEvent: (WorkEvent) -> Unit,
    appstate: ApprovalState,
    apponEvent: (ApprovalEvent) -> Unit,
    employeeWorkState: EmployeeWorkState,
    onEmployeeWorkEvent: (EmployeeWorkEvent) -> Unit,
    timeRangeState: TimeRangeState,
    onTimePickerEvent: (TimePickerEvent) -> Unit
    ){
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = MainScreen.valueOf(
        backStackEntry?.destination?.route ?: MainScreen.Login.name
    )
    Scaffold (topBar = {

    }){ innerPadding ->
    NavHost(
        navController = navController,
        startDestination = MainScreen.Login.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)){
        composable(route = MainScreen.Login.name){
                LoginScreen(
                    state = state,
                    onEvent = onEvent,
                    onFirstButtonClicked = { id, password ->
                        if (id == ManagerList.manager.id && password == ManagerList.manager.password) {
                            navController.navigate(MainScreen.ManagementHome.name)
                        }
                        if (state.validateLogin) {
                            navController.navigate(MainScreen.Home.name)
                        }
                    },
                    onSecondButtonClicked = {
                        navController.navigate(MainScreen.Register.name)

                    })
    }
        composable(route = MainScreen.Home.name){
          NormalEmployeeScreen(
              workState = workState,
              onWorkEvent = onWorkEvent,
              aprState = appstate,
              onAprEvent = apponEvent,
              employeeWorkListState = employeeWorkState,
              employeeWorkEvent = onEmployeeWorkEvent
          )
        }
           composable(route = MainScreen.Register.name) {
                RegisterScreen(
                    state = state,
                    onEvent =onEvent ,
                    onClickButton1 = {
                        navController.popBackStack()
                }
            )
        }

        composable(route = MainScreen.ManagementHome.name) {
            ManagementApp(
                state = workState,
                onEvent = onWorkEvent,
                timeRangeState = timeRangeState,
                onTimeEvent = onTimePickerEvent,
                empState = state,
                onEmpEvent = onEvent,
                aprState = appstate,
                onAprEvent = apponEvent
                )
        }

    }
    }
}

