package com.lemon.mango.application.model.task

import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class CreateTaskRequestModel(
    val name: String,
    val description: String?,
    val dueDate: LocalDateTime?,
    val targetDate: LocalDateTime?,
    val priority: Int?,
    val projectId: ProjectId?,
    val parentTaskId: TaskId?,
    val assigneeId: UserId?,
)
