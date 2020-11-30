package com.jhughes.getstuffdone.data.model

import androidx.room.*


@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title") val title: String
)


data class GroupAndTasks(
    @Embedded val group : GroupEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    )
    val tasks : List<TaskEntity> = emptyList()
)

