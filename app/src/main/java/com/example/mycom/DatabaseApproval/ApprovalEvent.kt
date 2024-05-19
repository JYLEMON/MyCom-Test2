package com.example.myapplication.DatabaseApproval

import com.example.myapplication.Database.Approval


sealed interface ApprovalEvent {
    object SaveApproval: ApprovalEvent
    data class SetStaffApprovalList(val staffid :String): ApprovalEvent
    data class SetAppReason(val appreason :String): ApprovalEvent
    data class SetLeaveandLate(val leaveandlate :String): ApprovalEvent
    data class SetStateInfo(val stateinfo :String): ApprovalEvent
    data class SetAppTime(val apptime :String): ApprovalEvent
    data class SetAppDate(val appdate :String): ApprovalEvent
    object Showlist: ApprovalEvent
    object  Hidelist: ApprovalEvent
    data class SortApproval(val approvalSortType:ApprovalSortType):ApprovalEvent
    data class DeleteApproval(val approval: Approval):ApprovalEvent
}