package com.jhughes.getstuffdone.domain.model

import java.util.*

data class GsdTask(
    val id: Int,
    val description: String,
    val isCompleted: Boolean,
    //val modifiedAt: Calendar
)