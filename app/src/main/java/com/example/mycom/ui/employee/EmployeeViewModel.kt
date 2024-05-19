package com.example.mycom.ui.employee

import PreferencesManager
import androidx.lifecycle.viewModelScope
import com.example.mycom.data.Employee
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class EmployeeViewModel(
    application: Application,
    private val dao: EmployeeDao
) : AndroidViewModel(application) {

    private val preferencesManager = PreferencesManager(application)

    private val _sortType = MutableStateFlow(SortType.EMP_ID)
    private val _employees = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.EMP_ID -> dao.getEmployeeOrderedById()
            }
        }

    private val _state = MutableStateFlow(EmployeeState(count = preferencesManager.getCount()))
    val state: StateFlow<EmployeeState> = combine(_state, _sortType, _employees) { state, sortType, employees ->
        state.copy(
            employee = employees,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EmployeeState())

    fun onEvent(event: EmployeeEvent) {
        when (event) {
            is EmployeeEvent.login -> {
                var employee: Employee? = null
                viewModelScope.launch {
                    employee = dao.login(event.id, event.password)
                }

                if (employee == null) {
                    return
                }

                _state.update {it.copy(
                    validateLogin = true
                )
                }
            }
            // Show/Hide Edit Dialog
            is EmployeeEvent.ShowEditDialog -> {
                _state.update { it.copy(isEditingEmployee = true, selectedEmployee = event.employee) }
            }
            EmployeeEvent.HideEditDialog -> {
                _state.update { it.copy(isEditingEmployee = false, selectedEmployee = null) }
            }

            // Update Employee
            is EmployeeEvent.UpdateEmployee -> {
                val selectedEmployee = state.value.selectedEmployee?.copy(
                    empName = event.empName,
                    email = event.email,
                    password = event.password,
                    salary = event.salary
                )
                if (selectedEmployee != null) {
                    viewModelScope.launch {
                        dao.updateEmployee(selectedEmployee)
                    }
                }
                _state.update { it.copy(isEditingEmployee = false, selectedEmployee = null) }
            }

            // Delete Employee
            is EmployeeEvent.DeleteEmployee -> {
                viewModelScope.launch {
                    dao.deleteEmployee(event.employee)
                }
            }
            EmployeeEvent.HideDeleteDialog -> {
                _state.update { it.copy(isDeletingEmployee = false) }
            }

            // Add Employee
            EmployeeEvent.HideDialog -> {
                _state.update { it.copy(isAddingEmployee = false) }
            }
            EmployeeEvent.SaveEmployee -> {
                val empName = state.value.empName
                val email = state.value.email
                val password = state.value.password
                val salary = state.value.salary
                val id = preferencesManager.getCount() + 1
                val employeeId = "E${id.toString().padStart(3, '0')}"

                if (empName.isBlank() || email.isBlank() || password.isBlank()) {
                    return
                }

                val employee = Employee(
                    empName = empName,
                    email = email,
                    password = password,
                    salary = salary.toString(),
                    empId = employeeId
                )
                viewModelScope.launch {
                    dao.upsertEmployee(employee)
                }
                preferencesManager.setCount(id)
                _state.update {
                    it.copy(
                        isAddingEmployee = false,
                        empName = "",
                        email = "",
                        password = "",
                        salary = 2500.00,
                        count = id
                    )
                }
            }

            // Setters
            is EmployeeEvent.SetEmail -> {
                _state.update { it.copy(email = event.email) }
            }
            is EmployeeEvent.SetName -> {
                _state.update { it.copy(empName = event.empName) }
            }
            is EmployeeEvent.SetPassword -> {
                _state.update { it.copy(password = event.password) }
            }
            is EmployeeEvent.SetSalary -> {
                _state.update { it.copy(salary = event.salary) }
            }

            // Dialog visibility
            EmployeeEvent.ShowDialog -> {
                _state.update { it.copy(isAddingEmployee = true) }
            }
            EmployeeEvent.ShowDeleteDialog -> {
                _state.update { it.copy(isDeletingEmployee = true) }
            }
            is EmployeeEvent.SortEmployee -> {
                _sortType.value = event.sortType
            }
            is EmployeeEvent.ShowDetailDialog -> {
                _state.update {
                    it.copy(
                        isShowingDetail = true,
                        selectedEmployee = event.employee
                    )
                }
            }
            EmployeeEvent.HideDetailDialog -> {
                _state.update { it.copy(isShowingDetail = false) }
            }
        }
    }
}
