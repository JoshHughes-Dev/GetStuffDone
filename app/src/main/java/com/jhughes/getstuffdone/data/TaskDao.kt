package com.jhughes.getstuffdone.data

import androidx.room.*
import com.jhughes.getstuffdone.data.model.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : TaskEntity)

    @Delete
    suspend fun deleteTask(task : TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks : List<TaskEntity>)
}