package com.example.mycom.ui.status

import com.example.mycom.EmployeeWorkData.EmployeeWork

data class EmployeeWorkState(
    val employeeWorkList: List<EmployeeWork> = emptyList(),
    val loginEmpID: String = "",
    val workID: String = "",
    val isAddingEmployeeWork: Boolean = false,
    val selectedEmployeeWork: EmployeeWork? = null,
    val sortType: EmployeeWorkSortType = EmployeeWorkSortType.EMP_ID
)
