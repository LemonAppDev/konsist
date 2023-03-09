package com.mango.domain.task.model

import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class Task(
    val id: TaskId,
    val name: String,
    val ownerId: UserId,
    val creationDate: LocalDateTime,
    val projectId: ProjectId? = null,
    val description: String? = null,
    val dueDate: LocalDateTime? = null,
    val targetDate: LocalDateTime? = null,
    val priority: Priority? = null,
    val parentTaskId: TaskId? = null,
    val assigneeId: UserId? = null,
    val completeDate: LocalDateTime? = null,
)
