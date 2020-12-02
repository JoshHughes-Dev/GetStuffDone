package com.jhughes.getstuffdone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val itemId: Int = 0,
    val groupId : Int,
    val description : String,
    val completed : Boolean = false,
)
