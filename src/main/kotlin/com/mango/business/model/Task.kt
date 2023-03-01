package com.mango.business.model

import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
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
