package com.example.myapplication.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.mycom.data.Employee
import kotlinx.coroutines.flow.Flow

@Dao

interface ApprovalStaffDao {

    @Upsert
    suspend fun insertApproval(approval: Approval)
    @Update
    suspend fun updateApproval(approval: Approval)

    @Delete
    suspend fun deleteApproval(approval: Approval)

    @Query("SELECT * FROM Approval ORDER BY staffid ASC")
    fun getSatffApprovallist(): Flow<List<Approval>>

}