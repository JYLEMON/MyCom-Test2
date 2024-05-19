package com.example.mycom.ui.status

import com.example.mycom.EmployeeWorkData.EmployeeWork

interface EmployeeWorkEvent {
    object SaveEmployeeWork: EmployeeWorkEvent

    data class SetEmployeeID(val empId: String): EmployeeWorkEvent
    data class SetWorkID(val workID: String): EmployeeWorkEvent

    data class DeleteEmployeeWork(val employeeWork: EmployeeWork): EmployeeWorkEvent

    object ShowDialog: EmployeeWorkEvent
    object HideDialog: EmployeeWorkEvent
}