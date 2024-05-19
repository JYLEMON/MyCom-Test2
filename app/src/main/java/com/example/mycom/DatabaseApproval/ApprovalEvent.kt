package com.example.myapplication.DatabaseApproval

import com.example.myapplication.Database.Approval
import com.example.mycom.data.Employee
import com.example.mycom.ui.employee.EmployeeEvent


sealed interface ApprovalEvent {
    data class UpdateApproval(
        val appreason: String,
        val leaveandlate : String,
        val stateinfo: String,
        val apptime:String,
        val appdate:String,
        val staffid: String,
        val name: String,
    ) : ApprovalEvent

    object SaveApproval: ApprovalEvent
    data class SetStaffApprovalList(val staffid :String): ApprovalEvent
    data class SetAppReason(val appreason :String): ApprovalEvent
    data class SetLeaveandLate(val leaveandlate :String): ApprovalEvent
    data class SetStateInfo(val stateinfo :String): ApprovalEvent
    data class Setname(val name :String): ApprovalEvent
    data class SetAppTime(val apptime :String): ApprovalEvent
    data class SetAppDate(val appdate :String): ApprovalEvent
    object Showlist: ApprovalEvent
    object  Hidelist: ApprovalEvent
    data class SortApproval(val approvalSortType:ApprovalSortType):ApprovalEvent
    data class DeleteApproval(val approval: Approval):ApprovalEvent
    data class ApproveApproval(val approval: Approval): ApprovalEvent
    data class RejectApproval(val approval: Approval): ApprovalEvent
    data class SelectApproval(val approval: Approval): ApprovalEvent
    object DismissApprovalDetail: ApprovalEvent
    object ShowDeleteDialog: ApprovalEvent
    object HideDeleteDialog: ApprovalEvent
    data class ShowDetailDialog(val approval: Approval): ApprovalEvent
    object HideDetailDialog: ApprovalEvent
    data class ShowEditDialog(val approval: Approval): ApprovalEvent
    object HideEditDialog: ApprovalEvent
}