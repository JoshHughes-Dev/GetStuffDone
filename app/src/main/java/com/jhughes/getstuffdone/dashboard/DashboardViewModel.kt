package com.jhughes.getstuffdone.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhughes.getstuffdone.domain.GroupRepository
import com.jhughes.getstuffdone.domain.TaskRepository
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(
    private val groupRepository: GroupRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val testTitles = listOf(
        "qwerty",
        "asdfgh",
        "zxcvbn",
        "12345678"
    )

    fun getGroups(): Flow<List<GsdGroup>> = groupRepository.getGroups()

    val selectedGroupId: MutableStateFlow<Int?> = MutableStateFlow(null)

    fun getSelectedGroup() = flowOf(
        selectedGroupId.flatMapLatest { groupId ->
            if (groupId != null) {
                groupRepository.getGroup(groupId)
            } else {
                emptyFlow()
            }
        }
    ).flattenMerge()

    fun saveTask(task: GsdTask) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.saveTask(task, selectedGroupId.value ?: -1) //todo
        }
    }

    fun deleteTask(task : GsdTask) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(task, selectedGroupId.value ?: -1) //todo
        }
    }

    fun testCreateNewGroup() {
        val gsdGroup = GsdGroup(title = testTitles.random())
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.createGroup(gsdGroup)
        }
    }
}