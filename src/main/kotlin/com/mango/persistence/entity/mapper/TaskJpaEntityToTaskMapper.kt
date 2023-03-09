package com.mango.persistence.entity.mapper

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.TaskJpaEntity
import org.springframework.stereotype.Service

@Service
class TaskJpaEntityToTaskMapper {
    operator fun invoke(taskJpaEntity: TaskJpaEntity) = Task(
        id = TaskId(taskJpaEntity.id),
        name = taskJpaEntity.name,
        ownerId = UserId(taskJpaEntity.ownerId),
        creationDate = taskJpaEntity.creationDate,
        projectId = taskJpaEntity.projectId?.let { ProjectId(it) },
        description = taskJpaEntity.description,
        dueDate = taskJpaEntity.dueDate,
        targetDate = taskJpaEntity.targetDate,
        priority = taskJpaEntity.priority?.let { Priority.getByValue(it) },
        parentTaskId = taskJpaEntity.parentTaskId?.let { TaskId(it) },
        assigneeId = taskJpaEntity.assigneeId?.let { UserId(it) },
        completeDate = taskJpaEntity.completeDate,
    )
}
