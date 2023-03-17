package com.mango.application.model.task

import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class UpdateTaskRequestModel(
    val taskId: TaskId,
    val name: String,
    val description: String?,
    val dueDate: LocalDateTime?,
    val targetDate: LocalDateTime?,
    val priority: Priority?,
    val projectId: ProjectId?,
    val parentTaskId: TaskId?,
    val assigneeId: UserId?,
    val isCompleted: Boolean,
)
