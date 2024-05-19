package com.example.mycom.ui.employee

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.mycom.data.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Upsert
    suspend fun upsertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    //
    @Query("SELECT * FROM Employee ORDER BY empId")
    fun getEmployeeOrderedById(): Flow<List<Employee>>
    @Query("SELECT * FROM Employee WHERE empId = :empId AND password = :password")
    suspend fun login(empId: String, password: String): Employee?
}