package com.example.managementsystem.ManagementModule

import com.example.managementsystem.Data.Work
import com.example.mycom.ui.ManagementModule.ManageWork.WorkSortType

sealed interface WorkEvent {
    object SaveWork:WorkEvent

    data class UpdateWork(
        val workTitle: String,
        val workDescription: String,
        val contactEmail: String
    ): WorkEvent

    data class SetTitle(val title: String): WorkEvent
    data class SetDescription(val description: String): WorkEvent
    data class SetEmail(val email:String): WorkEvent
    data class SetSelectedWork(val selectedWork: Work): WorkEvent

    object ShowDialog: WorkEvent
    object HideDialog: WorkEvent

    data class SortWork(val sortType: WorkSortType): WorkEvent
    data class DeleteWork(val work: Work): WorkEvent
}