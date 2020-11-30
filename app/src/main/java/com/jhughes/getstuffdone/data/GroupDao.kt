package com.jhughes.getstuffdone.data

import androidx.room.*
import com.jhughes.getstuffdone.data.model.GroupAndTasks
import com.jhughes.getstuffdone.data.model.GroupEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface GroupDao {

    @Transaction
    @Query("SELECT * FROM groups")
    fun getGroups() : Flow<List<GroupAndTasks>>

    @Transaction
    @Query("SELECT * FROM groups WHERE id = :groupId")
    fun getGroup(groupId : Int) : Flow<GroupAndTasks>

    fun getGroupDistinctUntilChanged(groupId : Int) : Flow<GroupAndTasks> {
        return getGroup(groupId).distinctUntilChanged()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(group : GroupEntity)

    @Delete
    fun deleteGroup(group : GroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(groups : List<GroupEntity>)
}