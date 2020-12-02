package com.jhughes.getstuffdone.domain

import com.jhughes.getstuffdone.domain.model.GsdTask

interface TaskRepository {

    suspend fun saveTask(task : GsdTask, groupId : Int)

    suspend fun deleteTask(task: GsdTask, groupId: Int)
}