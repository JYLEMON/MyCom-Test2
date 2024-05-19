package com.example.myapplication.DatabaseApproval

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.Approval
import com.example.myapplication.Database.ApprovalStaffDao
import com.example.myapplication.DatabaseAttendance.AttendanceDao
import com.example.mycom.ui.employee.EmployeeEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ApprovalViewModel(private val apprdao: ApprovalStaffDao): ViewModel()
{
    private val _Approval_sortType = MutableStateFlow(ApprovalSortType.Staff_Approval)
    private val _approval = _Approval_sortType
        .flatMapLatest { sortType -> when(sortType){
            ApprovalSortType.Staff_Approval ->apprdao.getSatffApprovallist()
        } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(ApprovalState())
    val state = combine(_state,_Approval_sortType,_approval){
            state,sortType,approval ->state.copy(
        approvalSortType = sortType,
        approval = approval
    )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ApprovalState())

    fun onEvent(event: ApprovalEvent){
        when(event){

            is ApprovalEvent.DeleteApproval -> {
                viewModelScope.launch {
                    apprdao.deleteApproval(event.approval)
                }

            }
            ApprovalEvent.Hidelist ->{
                _state.update { it.copy(isAddingApproval = false) }
            }

            ApprovalEvent.SaveApproval -> {
                val staffid = state.value.staffid
                val name =state.value.name
                val appreason = state.value.appreason
                val leaveandlate = state.value.leaveandlate
                val stateinfo = state.value.stateinfo
                val apptime = state.value.apptime
                val appdate = state.value.appdate



                if(staffid.isBlank()||appdate.isBlank()||appreason.isBlank()||
                    leaveandlate.isBlank()||stateinfo.isBlank()||apptime.isBlank()){
                    return
                }
                val approval = Approval(
                    staffid = staffid,
                    name = name,
                    appreason = appreason,
                    leaveandlate = leaveandlate,
                    stateinfo = stateinfo,
                    apptime = apptime,
                    appdate = appdate
                )
                viewModelScope.launch {
                    apprdao.insertApproval(approval)
                }
                _state.update { it.copy(
                    isAddingApproval = false,
                    name = "",
                    staffid = "",
                    appreason = "",
                    leaveandlate = "",
                    stateinfo = "",
                    apptime = "",
                    appdate = ""
                ) }
            }
            is ApprovalEvent.Setname -> {
                _state.update { it.copy(name = event.name) }
            }
            is ApprovalEvent.SetStaffApprovalList -> {
                _state.update { it.copy(staffid = event.staffid) }
            }
            is ApprovalEvent.SetAppReason -> {
                _state.update { it.copy(appreason = event.appreason) }
            }
            is ApprovalEvent.SetLeaveandLate -> {
                _state.update { it.copy(leaveandlate = event.leaveandlate) }
            }
            is ApprovalEvent.SetStateInfo -> {
                _state.update { it.copy(stateinfo = event.stateinfo) }
            }
            is ApprovalEvent.SetAppTime -> {
                _state.update { it.copy(apptime = event.apptime) }
            }
            is ApprovalEvent.SetAppDate -> {
                _state.update { it.copy(appdate = event.appdate) }
            }
            ApprovalEvent.Showlist -> {
                _state.update { it.copy(isAddingApproval = true) }
            }
            is ApprovalEvent.SortApproval -> {
                _Approval_sortType.value = event.approvalSortType
            }
            is ApprovalEvent.ApproveApproval -> {
                viewModelScope.launch {
                    val updatedApproval = event.approval.copy(stateinfo = "Approve")
                    apprdao.insertApproval(updatedApproval)
                }
            }
            is ApprovalEvent.RejectApproval -> {
                viewModelScope.launch {
                    val updatedApproval = event.approval.copy(stateinfo = "Reject")
                    apprdao.insertApproval(updatedApproval)
                }
            }
            is ApprovalEvent.SelectApproval -> {
                _state.update { it.copy(selectedApproval = event.approval) }
            }

            ApprovalEvent.DismissApprovalDetail -> {
                _state.update { it.copy(selectedApproval = null) }
            }

            ApprovalEvent.HideDeleteDialog -> {
                _state.update { it.copy(isDeletingApproval = false) }
            }
            ApprovalEvent.HideDetailDialog -> {
                _state.update { it.copy(isShowingDetail = false) }
            }
            ApprovalEvent.HideEditDialog -> {
                _state.update { it.copy(isEditingApproval = false, selectedApproval = null) }
            }
            ApprovalEvent.ShowDeleteDialog -> {
                _state.update { it.copy(isDeletingApproval = true) }
            }
            is ApprovalEvent.ShowDetailDialog -> {
                _state.update {
                    it.copy(
                        isShowingDetail = true,
                        selectedApproval = event.approval
                    )
                }
            }
            is ApprovalEvent.ShowEditDialog -> {
                _state.update { it.copy(isEditingApproval = true, selectedApproval = event.approval) }
            }
            is ApprovalEvent.UpdateApproval -> {
                val selectedApproval = state.value.selectedApproval?.copy(
                    appreason = event.appreason,
                    leaveandlate  = event.leaveandlate,
                    stateinfo = event.stateinfo,
                    apptime = event.apptime,
                    appdate = event.appdate,
                    staffid = event.staffid,
                    name = event.name,
                )
                if (selectedApproval != null) {
                    viewModelScope.launch {
                        apprdao.updateApproval(selectedApproval)
                    }
                }
                _state.update { it.copy(isEditingApproval = false, selectedApproval = null) }
            }
        }
    }
}