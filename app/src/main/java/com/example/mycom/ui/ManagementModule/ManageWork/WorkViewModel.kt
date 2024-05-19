package com.example.mycom.ui.ManagementModule.ManageWork

import PreferencesManager
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.managementsystem.Data.Work
import com.example.managementsystem.ManagementModule.WorkEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkViewModel(
    application: Application,
    private val dao: WorkDao
): AndroidViewModel(application) {

    private val preferencesManager = PreferencesManager(application)

    private val _sortType = MutableStateFlow(WorkSortType.WORK_ID)
    private val _workList = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                WorkSortType.WORK_ID -> dao.getWorkListOrderById()
            }
        }
    private val _state = MutableStateFlow(WorkState(count = preferencesManager.getCount()))
    val state = combine(_state, _sortType, _workList) { state, sortType, workList ->
        state.copy(
            workList = workList,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WorkState())

    fun onEvent(event: WorkEvent) {
        when(event) {
            is WorkEvent.DeleteWork -> {
                viewModelScope.launch {
                    dao.deleteWork(event.work)
                }
            }
            WorkEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingWork = false
                ) }
            }

            is WorkEvent.UpdateWork -> {
                val selectedWork = state.value.selectedWork?.copy(
                    workTitle = event.workTitle,
                    workDescription = event.workDescription,
                    contactEmail = event.contactEmail
                )
                if (selectedWork != null) {
                    viewModelScope.launch {
                        dao.updateWork(selectedWork)
                    }
                }
                _state.update { it.copy(selectedWork = null) }
            }

            WorkEvent.SaveWork -> {
                val workTitle = state.value.workTitle
                val workDescription = state.value.workDescription
                val handlerEmail = state.value.handlerEmail
                val id = preferencesManager.getCount() + 1
                val workID = "W${id.toString().padStart(3,'0')}"

                if(workTitle.isBlank() || workDescription.isBlank()) {
                    return
                }

                val work = Work(
                    workTitle = workTitle,
                    workDescription = workDescription,
                    contactEmail = handlerEmail,
                    workID = workID
                )
                viewModelScope.launch {
                    dao.upsertWork(work)
                }
                preferencesManager.setCount(id)
                _state.update { it.copy(
                    isAddingWork = false,
                    workTitle = "",
                    workDescription = "",
                    handlerEmail = "",
                    workID = "",
                    count = id
                ) }
            }

            is WorkEvent.SetSelectedWork -> {
                _state.update { it.copy(
                    selectedWork = event.selectedWork
                ) }
            }

            is WorkEvent.SetTitle -> {
                _state.update { it.copy(
                    workTitle = event.title
                ) }
            }
            is WorkEvent.SetDescription -> {
                _state.update { it.copy(
                    workDescription = event.description
                ) }
            }
            is WorkEvent.SetEmail -> {
                _state.update { it.copy(
                    handlerEmail = event.email
                ) }
            }
            WorkEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingWork = true
                ) }
            }
            is WorkEvent.SortWork -> {
                _sortType.value = event.sortType
            }
            else -> {}
        }
    }
}