package com.example.managementsystem.ManagementModule

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.managementsystem.Data.Work
import com.example.mycom.R
import com.example.mycom.ui.ManagementModule.ManageWork.WorkState
import com.example.mycom.ui.ManagementModule.RuleModify.TimePickerEvent
import com.example.mycom.ui.ManagementModule.RuleModify.TimeRangeState
import com.example.mycom.ui.theme.MyComTheme

enum class ManagementScreen(@StringRes val title: Int) {
    managementMain(title = R.string.app_name),
    ruleSet(title = R.string.rules),
    displayWork(title = R.string.displayWorkList),
    workAssign(title = R.string.workAssign),
    detailedWork(title = R.string.workDetail),
    modifyWork(title = R.string.modifyWork)
}

@Composable
fun NavigationBar(
    goSetRulesButtonClicked: () -> Unit = {},
    goShowWorkButtonClicked: () -> Unit = {},
    currentScreen: ManagementScreen,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .fillMaxWidth(),
        actions = {
            IconButton(onClick = goSetRulesButtonClicked) {
                Icon(
                    Icons.Outlined.DateRange,
                    contentDescription = "Rule Set"
                )
            }
            IconButton(onClick = goShowWorkButtonClicked) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Add Work"
                )
            }
        }
    )
}

@Composable
fun ManagementApp(
    navController: NavHostController = rememberNavController(),
    state: WorkState,
    onEvent: (WorkEvent) -> Unit,
    timeRangeState: TimeRangeState,
    onTimeEvent: (TimePickerEvent) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ManagementScreen.valueOf(
        backStackEntry?.destination?.route ?: ManagementScreen.managementMain.name
    )

    var selectedWork by remember { mutableStateOf<Work?>(null) }

    val onDeleteWork: (Work) -> Unit = { work ->
        onEvent(WorkEvent.DeleteWork(work))
    }

    //Insert Default Data
    //onTimeEvent(TimePickerEvent.SaveDefaultTime)

    Scaffold(
        bottomBar = {
            NavigationBar(
                currentScreen = currentScreen,
                navigateUp = { navController.navigateUp() },
                goSetRulesButtonClicked = {navController.navigate(ManagementScreen.ruleSet.name)},
                goShowWorkButtonClicked = {navController.navigate(ManagementScreen.displayWork.name)}
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ManagementScreen.managementMain.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ManagementScreen.managementMain.name) {
                MainScreen(navController)
            }
            composable(route = ManagementScreen.ruleSet.name) {
                ShowRulesScreen(state = timeRangeState, onEvent = onTimeEvent)
            }
            composable(route = ManagementScreen.workAssign.name) {
                AddWorkScreen(state = state, onEvent = onEvent) {
                    navController.popBackStack()
                }
            }
            composable(route = ManagementScreen.displayWork.name) {
                DisplayWorkListScreen(
                    state = state,
                    onNextButtonPress = {
                        navController.navigate(ManagementScreen.workAssign.name)
                    },
                    onWorkSelected = { work ->
                        selectedWork = work
                        onEvent(WorkEvent.SetSelectedWork(work))
                        navController.navigate(ManagementScreen.detailedWork.name)
                    },
                    onDeleteWork = onDeleteWork
                )
            }
            composable(route = ManagementScreen.detailedWork.name) {
                selectedWork?.let { work ->
                    WorkDetailScreen(
                        workDetail = WorkDetail(
                            title = work.workTitle,
                            id = work.workID,
                            description = work.workDescription,
                            email = work.contactEmail
                        ),
                        onEditClick = {
                            navController.navigate(ManagementScreen.modifyWork.name)
                        },
                        onWorkDetailsChange = {}
                    )
                }
            }
            composable(route = ManagementScreen.modifyWork.name) {
                selectedWork?.let {work ->
                    ModifyWorkScreen(
                        initialTitleField = work.workTitle,
                        initialDescriptionField = work.workDescription,
                        initialEmailField = work.contactEmail
                    ) { updatedTitle, updatedDescription, updatedEmail ->
                        if(state.selectedWork != null){
                            onEvent(
                                WorkEvent.UpdateWork(
                                    workTitle = updatedTitle,
                                    workDescription = updatedDescription,
                                    contactEmail = updatedEmail
                                )
                            )
                        }
                        selectedWork = Work(
                            workTitle = updatedTitle,
                            workDescription = updatedDescription,
                            contactEmail = updatedEmail,
                            workID = work.workID
                        )
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun managementSystemPreview(){
    MyComTheme {
        ManagementApp(
            state = WorkState(),
            onEvent = {},
            timeRangeState = TimeRangeState(),
            onTimeEvent = {}
        )
    }
}