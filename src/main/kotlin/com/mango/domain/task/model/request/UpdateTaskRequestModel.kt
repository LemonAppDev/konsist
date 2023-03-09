package com.mango.domain.task.model.request

import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class UpdateTaskRequestModel(
    val taskId: TaskId,
    val name: String? = null,
    val description: String? = null,
    val dueDate: LocalDateTime? = null,
    val targetDate: LocalDateTime? = null,
    val priority: Priority? = null,
    val projectId: ProjectId? = null,
    val parentTaskId: TaskId? = null,
    val assigneeId: UserId? = null,
    val isCompleted: Boolean? = null,
)
