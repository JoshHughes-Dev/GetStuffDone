package com.jhughes.getstuffdone.domain

import com.jhughes.getstuffdone.domain.model.GsdGroup
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    fun getGroups() : Flow<List<GsdGroup>>

    fun getGroup(id: Int) : Flow<GsdGroup>

    suspend fun saveGroup(gsdGroup: GsdGroup) : Int

    suspend fun deleteGroup(gsdGroup: GsdGroup)
}