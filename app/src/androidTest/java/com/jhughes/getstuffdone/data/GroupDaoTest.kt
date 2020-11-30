package com.jhughes.getstuffdone.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.jhughes.getstuffdone.data.model.GroupEntity
import com.jhughes.getstuffdone.data.model.TaskEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GroupDaoTest {

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

    @Test
    fun testGetGroups() = runBlocking {
        val groupEntity = GroupEntity(id = 1, title = "qwerty")
        val groupEntity2 = GroupEntity(id = 2, title = "asdf")

        groupDao.insertAll(listOf(groupEntity, groupEntity2))

        groupDao.getGroups().collect { groups ->
            assertThat(groups).hasSize(2)
        }
    }

    @Test
    fun testGetGroup() = runBlocking {
        val task = TaskEntity(itemId = 1, groupId = 1, description = "qwerty")
        val task2 = TaskEntity(itemId = 1, groupId = 1, description = "qwerty")
        val groupEntity = GroupEntity(id = 1, title = "qwerty")

        groupDao.insertAll(listOf(groupEntity))
        taskDao.insertAll(listOf(task, task2))

        groupDao.getGroup(1).collect { groupAndTasks ->
            assertThat(groupAndTasks.group.id).isEqualTo(1)
            assertThat(groupAndTasks.tasks).hasSize(2)
        }
    }
}