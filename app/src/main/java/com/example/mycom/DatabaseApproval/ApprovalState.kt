package com.example.myapplication.DatabaseApproval

import com.example.myapplication.Database.Approval


data class ApprovalState(
    val approval: List<Approval> = emptyList(),
    val staffid : String ="",
    val apptime : String ="",
    val appreason : String ="",
    val appdate : String ="",
    val leaveandlate : String ="",
    val stateinfo : String ="",
    val isAddingApproval: Boolean =false,
    val approvalSortType: ApprovalSortType = ApprovalSortType.Staff_Approval

)
