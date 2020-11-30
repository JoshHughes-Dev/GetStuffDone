package com.jhughes.getstuffdone.dashboard

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhughes.getstuffdone.domain.GroupRepository
import com.jhughes.getstuffdone.domain.model.GsdGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(
    private val groupRepository: GroupRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val testTitles = listOf(
        "qwerty",
        "asdfgh",
        "zxcvbn",
        "12345678"
    )

    fun getGroups() : Flow<List<GsdGroup>> = groupRepository.getGroups()

    fun testCreateNewGroup() {
        val gsdGroup = GsdGroup(title = testTitles.random())
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.createGroup(gsdGroup)
        }
    }
}