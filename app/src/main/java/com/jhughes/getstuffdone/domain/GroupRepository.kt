package com.jhughes.getstuffdone.domain

import com.jhughes.getstuffdone.domain.model.GsdGroup
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    fun getGroups() : Flow<List<GsdGroup>>

    fun getGroup(id: Int) : Flow<GsdGroup>

    suspend fun createGroup(gsdGroup: GsdGroup)

    suspend fun deleteGroup(gsdGroup: GsdGroup)
}