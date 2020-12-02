package com.jhughes.getstuffdone.groupdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.jhughes.getstuffdone.domain.GroupRepository
import com.jhughes.getstuffdone.domain.TaskRepository

class GroupDetailsViewModel @ViewModelInject constructor(
    private val groupRepository: GroupRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

}