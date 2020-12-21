package com.jhughes.getstuffdone.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhughes.getstuffdone.domain.GroupRepository
import com.jhughes.getstuffdone.domain.TaskRepository
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DashboardViewModel @ViewModelInject constructor(
    private val groupRepository: GroupRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun getGroups(): Flow<List<GsdGroup>> = groupRepository.getGroups()

    val selectedGroupId: MutableStateFlow<Int?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun getSelectedGroup() = flowOf(
        selectedGroupId.flatMapLatest { groupId ->
            if (groupId != null) {
                groupRepository.getGroup(groupId)
            } else {
                emptyFlow()
            }
        }
    ).flattenMerge()

    fun createGroup(group: GsdGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedGroupId.value = groupRepository.createGroup(group)
        }
    }

    fun saveGroup(group: GsdGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.updateGroup(group)
        }
    }

    fun deleteGroup(group: GsdGroup) {
        selectedGroupId.value = null
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.deleteGroup(group)
        }
    }

    fun saveTask(task: GsdTask) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.saveTask(task, selectedGroupId.value!!)
        }
    }

    fun deleteTask(task: GsdTask) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(task, selectedGroupId.value!!)
        }
    }
}