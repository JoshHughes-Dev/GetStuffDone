package com.jhughes.getstuffdone.data.seeder

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jhughes.getstuffdone.data.AppDatabase
import com.jhughes.getstuffdone.data.domainGroupToLocalGroup
import com.jhughes.getstuffdone.data.model.GroupAndTasks
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {

        val groupOne = GsdGroup(
            id = 1,
            title = "Shopping List!!!",
            tasks = listOf(
                GsdTask(1, "Milk", true),
                GsdTask(6, "Eggs", false),
                GsdTask(3, "Butter", false),
                GsdTask(8, "Bread", false)
            )
        )

        val groupTwo = GsdGroup(
            id = 1,
            title = "Weekend prep",
            tasks = listOf(
                GsdTask(5, "Make a plan", false),
                GsdTask(2, "Tell people the plan", false),
                GsdTask(7, "Do the plan", false),
                GsdTask(4, "pat yourself on the back for a job well done", false)
            )
        )

        val localGroupsAndTasks: List<GroupAndTasks> = listOf(groupOne, groupTwo).map { gsdGroup ->
            domainGroupToLocalGroup(gsdGroup)
        }

        val database = AppDatabase.getInstance(applicationContext)
        database.groupDao().insertAll(localGroupsAndTasks.map { it.group })
        database.taskDao().insertAll(localGroupsAndTasks.flatMap { it.tasks })

        Result.success()
    }
}
