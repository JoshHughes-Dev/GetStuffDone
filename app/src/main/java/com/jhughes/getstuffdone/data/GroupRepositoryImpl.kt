package com.jhughes.getstuffdone.data

import com.jhughes.getstuffdone.data.model.GroupEntity
import com.jhughes.getstuffdone.domain.GroupRepository
import com.jhughes.getstuffdone.domain.model.GsdGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepositoryImpl @Inject constructor(private val groupDao: GroupDao) : GroupRepository {

    override fun getGroups(): Flow<List<GsdGroup>> {
        //todo this seems expensive?
        return groupDao.getGroups().map { groups ->
            groups.map { localGroupToDomainGroup(it) }
        }
    }

    override fun getGroup(id: Int): Flow<GsdGroup> {
        //todo this seems expensive?
        return groupDao.getGroupDistinctUntilChanged(id).map { group ->
            localGroupToDomainGroup(group)
        }
    }

    override suspend fun createGroup(gsdGroup: GsdGroup) {
        val group = domainGroupToLocalGroup(gsdGroup).group
        groupDao.insertGroup(group)
    }

    override suspend fun deleteGroup(gsdGroup: GsdGroup) {
        val group = domainGroupToLocalGroup(gsdGroup).group
        groupDao.deleteGroup(group)
    }
}