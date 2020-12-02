package com.jhughes.getstuffdone.domain.model

data class GsdTask(val id: Int, val description: String, val isCompleted: Boolean) {

    companion object {

        fun createEmpty() : GsdTask {
            return GsdTask(0, "", false)
        }
    }
}