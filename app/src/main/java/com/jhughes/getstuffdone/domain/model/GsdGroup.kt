package com.jhughes.getstuffdone.domain.model

data class GsdGroup(val id: Int = 0, val title: String, val tasks: List<GsdTask> = emptyList()) {

    fun isCompleted() : Boolean {
        return tasks.any { !it.isCompleted }.not()
    }
}