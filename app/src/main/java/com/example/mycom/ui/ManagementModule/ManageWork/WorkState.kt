package com.example.mycom.ui.ManagementModule.ManageWork

import com.example.managementsystem.Data.Work
import com.example.mycom.ui.ManagementModule.ManageWork.WorkSortType

data class WorkState(
    val workList: List<Work> = emptyList(),
    val workTitle: String = "",
    val workDescription: String = "",
    val handlerEmail: String = "",
    val workID: String = "",
    val count: Int = 0,
    val isAddingWork: Boolean = false,
    val selectedWork: Work? = null,
    val sortType: WorkSortType = WorkSortType.WORK_ID
)
