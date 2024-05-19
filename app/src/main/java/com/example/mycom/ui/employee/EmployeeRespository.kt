package com.example.mycom.ui.employee

import com.example.mycom.data.Employee

class EmployeeRespository(private val employeeDao: EmployeeDao) {
    suspend fun authenticate(id: String, password: String): Employee? {
        return employeeDao.login(id, password)
    }
}