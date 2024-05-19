package com.example.mycom.ui.employee

import com.example.mycom.data.Employee

data class EmployeeState (
    val employee: List<Employee> = emptyList(),
    val empName: String = "",
    val email: String = "",
    val password: String = "",
    val salary: Double = 2500.00,
    val count: Int = 0,
    val deleteEmployee: Employee? = null,
    val isAddingEmployee: Boolean = false,
    val isShowingDetail: Boolean = false,
    val selectedEmployee: Employee? = null,
    val isDeletingEmployee: Boolean = false,
    val isEditingEmployee: Boolean = false,
    val sortType: SortType = SortType.EMP_ID,
    val validateLogin: Boolean = false
)
