package com.jhughes.getstuffdone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "tasks",
    foreignKeys = [ForeignKey(
        entity = GroupEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("groupId"),
        onDelete = CASCADE
    )]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val itemId: Int = 0,
    val groupId: Int,
    val description: String,
    val completed: Boolean = false,
    //val modifiedAt : Calendar
)
