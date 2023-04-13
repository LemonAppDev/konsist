package com.lemon.mango.domain.task.model

import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class Task(
    val id: TaskId,
    val name: String,
    val ownerId: UserId,
    val creationDate: LocalDateTime,
    val projectId: ProjectId?,
    val description: String?,
    val dueDate: LocalDateTime?,
    val targetDate: LocalDateTime?,
    val priority: Priority?,
    val parentTaskId: TaskId?,
    val assigneeId: UserId?,
    val completeDate: LocalDateTime?,
)
