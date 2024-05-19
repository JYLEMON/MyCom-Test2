package com.example.mycom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.managementsystem.Data.WorkDatabase
import com.example.managementsystem.ManagementModule.ManagementApp
import com.example.mycom.ui.ManagementModule.ManageWork.WorkViewModel
import com.example.myapplication.Database.ApprovalDatabase
import com.example.myapplication.DatabaseApproval.ApprovalViewModel
import com.example.myapplication.DatabaseAttendance.AttendanceDatabase
import com.example.myapplication.DatabaseAttendance.AttendanceViewModel
import com.example.myapplication.app
import com.example.mycom.data.EmployeeDatabase
import com.example.mycom.timeRangeData.TimeRangeDatabase
import com.example.mycom.ui.ManagementModule.RuleModify.TimePickerEvent
import com.example.mycom.ui.ManagementModule.RuleModify.TimeRangeState
import com.example.mycom.ui.employee.EmployeeViewModel
import com.example.mycom.ui.ManagementModule.RuleModify.TimeRangeViewModel
import com.example.mycom.ui.theme.MyComTheme

class MainActivity : ComponentActivity() {
    private val appr by lazy {
        Room.databaseBuilder(
            applicationContext,
            ApprovalDatabase::class.java,
            "approval.appr"
        ).build()
    }
    private val atte by lazy {
        Room.databaseBuilder(
            applicationContext,
            AttendanceDatabase::class.java,
            "Attendance.atte"
        ).build()
    }
    private val approvalviewModel by viewModels<ApprovalViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ApprovalViewModel(appr.apprdao) as T
                }
            }
        }
    )
    private val attendanceviewModel by viewModels<AttendanceViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AttendanceViewModel(atte.attdao) as T
                }
            }
        }
    )

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            EmployeeDatabase::class.java,
            "employee.db"
        ).build()
    }

    private val viewModel by viewModels<EmployeeViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return EmployeeViewModel(application, db.dao) as T
                }
            }
        }
    )

    private val workdb by lazy {
        Room.databaseBuilder(
            applicationContext,
            WorkDatabase::class.java,
            "workList.db"
        ).build()
    }

    private val workViewModel by viewModels<WorkViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <U : ViewModel> create(modelClass: Class<U>): U {
                    return WorkViewModel(application, workdb.dao) as U
                }
            }
        }
    )

    private val timedb by lazy {
        Room.databaseBuilder(
            applicationContext,
            TimeRangeDatabase::class.java,
            "timeRange.db"
        ).build()
    }

    private val timeRangeViewModel by viewModels<TimeRangeViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <V : ViewModel> create(modelClass: Class<V>): V {
                    return TimeRangeViewModel(timedb.dao) as V
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyComTheme {
                val state by viewModel.state.collectAsState()
                val workState by workViewModel.state.collectAsState()
                val timeRangeState by timeRangeViewModel.state.collectAsState()
                //EmployeeScreenTest(state = state, onEvent = viewModel::onEvent)
                val apprstate by approvalviewModel.state.collectAsState()
                val Attesyaye by attendanceviewModel.state.collectAsState()
                //StaffApprovalScreen(state = apprstate, onEvent = approvalviewModel::onEvent)
                app(state = state, onEvent = viewModel::onEvent, appstate = apprstate, apponEvent = approvalviewModel::onEvent)
                // RegisterScreen(state = state, onEvent = viewModel::onEvent)

                if (!OneTimeRunUtil.hasRun(this)) {
                    // Your one-time code here
                    executeOneTimeCode(state = timeRangeState, onEvent = timeRangeViewModel::onEvent)

                    // Set the flag indicating the code has run
                    OneTimeRunUtil.setHasRun(this)
                }

                /*ManagementApp(
                    state = workState,
                    onEvent = workViewModel::onEvent,
                    timeRangeState = timeRangeState,
                    onTimeEvent = timeRangeViewModel::onEvent
                )*/
            }
        }
    }

    private fun executeOneTimeCode(state: TimeRangeState, onEvent: (TimePickerEvent) -> Unit) {
        // Your code that should run only once per device
        onEvent(TimePickerEvent.SaveDefaultTime)
    }
}
