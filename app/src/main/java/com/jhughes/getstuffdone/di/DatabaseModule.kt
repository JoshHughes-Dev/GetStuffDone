package com.jhughes.getstuffdone.di

import android.content.Context
import com.jhughes.getstuffdone.data.AppDatabase
import com.jhughes.getstuffdone.data.GroupDao
import com.jhughes.getstuffdone.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideTasksDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    fun provideGroupsDao(appDatabase: AppDatabase): GroupDao {
        return appDatabase.groupDao()
    }
}