package com.example.mycom.ui.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managementsystem.ManagementModule.EmployeeWorkDao
import com.example.mycom.EmployeeWorkData.EmployeeWork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeWorkViewModel(
    private val dao: EmployeeWorkDao
): ViewModel() {
    private val _sortType = MutableStateFlow(EmployeeWorkSortType.EMP_ID)
    private val _employeeWorkList = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                EmployeeWorkSortType.EMP_ID -> dao.getEmployeeWorkListOrderById()
            }
        }
    private val _state = MutableStateFlow(EmployeeWorkState())
    val state = combine(_state, _sortType, _employeeWorkList) { state, sortType, employeeWorkList ->
        state.copy(
            employeeWorkList = employeeWorkList,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EmployeeWorkState())

    fun onEvent(event: EmployeeWorkEvent) {
        when(event) {
            is EmployeeWorkEvent.DeleteEmployeeWork -> {
                viewModelScope.launch {
                    dao.deleteEmployeeWork(event.employeeWork)
                }
            }
            EmployeeWorkEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingEmployeeWork = true
                ) }
            }
            EmployeeWorkEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingEmployeeWork = false
                ) }
            }
            EmployeeWorkEvent.SaveEmployeeWork -> {
                val empID = state.value.loginEmpID
                val workID = state.value.workID

                if (workID.isBlank()) {
                    return
                }

                val employeeWork = EmployeeWork(
                    empId = empID,
                    workID = workID
                )
                viewModelScope.launch {
                    dao.upsertEmployeeWork(employeeWork)
                }
                _state.update { it.copy(
                    workID = ""
                ) }
            }

            is EmployeeWorkEvent.SetEmployeeID -> {
                _state.update { it.copy(
                    loginEmpID = event.empId
                ) }
            }
            is EmployeeWorkEvent.SetWorkID -> {
                _state.update { it.copy(
                    workID = event.workID
                ) }
            }
        }
    }
}