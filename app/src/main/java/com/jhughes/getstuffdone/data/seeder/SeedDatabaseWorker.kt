package com.jhughes.getstuffdone.data.seeder

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.jhughes.getstuffdone.data.AppDatabase
import com.jhughes.getstuffdone.data.domainGroupToLocalGroup
import com.jhughes.getstuffdone.data.model.GroupAndTasks
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime
import java.util.*

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {

        val groupOne = GsdGroup(
            id = 1,
            title = "Shopping list",
            tasks = listOf(
                GsdTask(1, "Eggs", true),
                GsdTask(6, "Semi-skimmed Milk", true),
                GsdTask(3, "Butter", true),
                GsdTask(8, "English Muffin", false),
                GsdTask(9, "Bread", false),
                GsdTask(10, "Sweet Potatoes", false)
            )
        )

        val groupTwo = GsdGroup(
            id = 2,
            title = "Weekend prep",
            tasks = listOf(
                GsdTask(5, "Make a plan", false),
                GsdTask(2, "Tell people the plan", false),
                GsdTask(7, "Do the plan", false),
                GsdTask(4, "pat yourself on the back for a job well done", false)
            )
        )

        val groupThree = GsdGroup(
            id = 3,
            title = "Things I need to do for interview",
            tasks = listOf(
                GsdTask(11, "update my CV", true),
                GsdTask(12, "make my Github look great", true),
                GsdTask(13, "clean up my LinkedIn", true),
            )
        )

        val groupFour = GsdGroup(
            id = 4,
            title = "Christmas present Idea",
            tasks = listOf(
                GsdTask(14, "Christmas Jumper", false),
                GsdTask(15, "bottle of wine", false),
                GsdTask(16, "big speaker system", true),
                GsdTask(17, "new xbox", false),
            )
        )

        val groupFive = GsdGroup(
            id = 5,
            title = "Donzo list",
            tasks = listOf(
                GsdTask(18, "Did that thing", true),
                GsdTask(19, "made that thing", true),
                GsdTask(20, "washed that thing", true),
                GsdTask(21, "ate that thing", true),
                GsdTask(22, "listened to that thing", true),
            )
        )

        val groupSix = GsdGroup(
            id = 6,
            title = "Short list",
            tasks = listOf(
                GsdTask(23, "Did adf thing", false),
                GsdTask(24, "made adsf thing", false),
            )
        )

        val localGroupsAndTasks: List<GroupAndTasks> =
            listOf(groupOne, groupTwo, groupThree, groupFour, groupFive, groupSix).map { gsdGroup ->
                domainGroupToLocalGroup(gsdGroup)
            }

        val database = AppDatabase.getInstance(applicationContext)
        database.groupDao().insertAll(localGroupsAndTasks.map { it.group })
        database.taskDao().insertAll(localGroupsAndTasks.flatMap { it.tasks })

        Result.success()
    }
}
