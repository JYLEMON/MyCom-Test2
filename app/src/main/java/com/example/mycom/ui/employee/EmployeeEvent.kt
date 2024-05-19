package com.example.mycom.ui.employee

import com.example.mycom.data.Employee

sealed interface EmployeeEvent {
    //Save Data
    object SaveEmployee: EmployeeEvent

    //Update
    data class UpdateEmployee(
        val empName: String,
        val email: String,
        val password: String,
        val salary: String
    ) : EmployeeEvent

    //Name
    data class SetName(val empName: String): EmployeeEvent
    data class SetEmail(val email: String): EmployeeEvent
    data class SetPassword(val password: String): EmployeeEvent
    data class SetSalary(val salary: Double): EmployeeEvent

    //Add Dialog
    object  ShowDialog: EmployeeEvent
    object HideDialog: EmployeeEvent

    //Delete Dialog
    object ShowDeleteDialog: EmployeeEvent
    object HideDeleteDialog: EmployeeEvent

    //Detail Dialog
    data class ShowDetailDialog(val employee: Employee): EmployeeEvent
    object HideDetailDialog: EmployeeEvent

    //Edit Dialog
    data class ShowEditDialog(val employee: Employee): EmployeeEvent
    object HideEditDialog: EmployeeEvent

    data class SortEmployee(val sortType: SortType): EmployeeEvent
    data class DeleteEmployee(val employee: Employee): EmployeeEvent

    data class login(val id: String, val password: String): EmployeeEvent
}