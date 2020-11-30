package com.jhughes.getstuffdone.data

import com.jhughes.getstuffdone.domain.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {
}