package com.jhughes.getstuffdone.data

import com.jhughes.getstuffdone.domain.TaskRepository
import com.jhughes.getstuffdone.domain.model.GsdTask
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {

    override suspend fun saveTask(task: GsdTask, groupId : Int) {
        taskDao.insertTask(domainTaskToLocalTask(task, groupId))
    }

    override suspend fun deleteTask(task: GsdTask, groupId : Int) {
        taskDao.deleteTask(domainTaskToLocalTask(task, groupId))
    }
}