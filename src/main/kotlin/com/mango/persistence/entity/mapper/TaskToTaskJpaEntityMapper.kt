package com.mango.persistence.entity.mapper

import com.mango.business.model.Task
import com.mango.persistence.entity.TaskJpaEntity
import org.springframework.stereotype.Service

@Service
class TaskToTaskJpaEntityMapper {
    operator fun invoke(task: Task) = TaskJpaEntity(
        id = task.id.value,
        name = task.name,
        ownerId = task.ownerId.value,
        creationDate = task.creationDate,
        projectId = task.projectId?.value,
        description = task.description,
        dueDate = task.dueDate,
        targetDate = task.targetDate,
        priority = task.priority?.value,
        parentTaskId = task.parentTaskId?.value,
        assigneeId = task.assigneeId?.value,
        completeDate = task.completeDate,
    )
}
