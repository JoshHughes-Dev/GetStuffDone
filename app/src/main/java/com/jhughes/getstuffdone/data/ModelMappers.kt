package com.jhughes.getstuffdone.data

import com.jhughes.getstuffdone.data.model.GroupAndTasks
import com.jhughes.getstuffdone.data.model.GroupEntity
import com.jhughes.getstuffdone.data.model.TaskEntity
import com.jhughes.getstuffdone.domain.model.GsdGroup
import com.jhughes.getstuffdone.domain.model.GsdTask


fun localGroupToDomainGroup(groupAndTasks: GroupAndTasks) : GsdGroup {
    return GsdGroup(
        id = groupAndTasks.group.id,
        title = groupAndTasks.group.title,
        tasks = groupAndTasks.tasks.map { localTaskToDomainTask(it) }
    )
}

fun localTaskToDomainTask(taskEntity: TaskEntity) : GsdTask {
    return GsdTask(
        id = taskEntity.itemId,
        description = taskEntity.description,
        isCompleted = taskEntity.completed
    )
}

fun domainGroupToLocalGroup(gsdGroup: GsdGroup) : GroupAndTasks {
    return GroupAndTasks(
        group = GroupEntity(gsdGroup.id, gsdGroup.title),
        tasks = gsdGroup.tasks.map { domainTaskToLocalTask(it, gsdGroup.id) }
    )
}

fun domainTaskToLocalTask(gsdTask: GsdTask, groupId : Int) : TaskEntity {
    return TaskEntity(
        itemId = gsdTask.id,
        groupId = groupId,
        description = gsdTask.description,
        completed = gsdTask.isCompleted
    )
}