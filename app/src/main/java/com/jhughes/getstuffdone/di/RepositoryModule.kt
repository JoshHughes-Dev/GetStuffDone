package com.jhughes.getstuffdone.di

import com.jhughes.getstuffdone.data.GroupRepositoryImpl
import com.jhughes.getstuffdone.data.TaskRepositoryImpl
import com.jhughes.getstuffdone.domain.GroupRepository
import com.jhughes.getstuffdone.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository

    @Binds
    abstract fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository
}