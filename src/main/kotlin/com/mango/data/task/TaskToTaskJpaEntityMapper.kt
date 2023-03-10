package com.mango.data.task

import com.mango.domain.task.model.Task
import org.springframework.stereotype.Service

@Service
class TaskToTaskJpaEntityMapper {
    operator fun invoke(task: Task) = TaskJpaEntity(
        id = task.value.value,
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
