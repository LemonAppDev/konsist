package com.lemon.mango.data.task

import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.model.Priority
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
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
