package com.jhughes.getstuffdone.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule

class TaskDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var groupDao: GroupDao
    private lateinit var taskDao : TaskDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        groupDao = database.groupDao()
        taskDao = database.taskDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    //todo
}