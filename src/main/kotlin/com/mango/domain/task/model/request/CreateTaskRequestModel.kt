package com.mango.domain.task.model.request

import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class CreateTaskRequestModel(
    val name: String,
    val description: String? = null,
    val dueDate: LocalDateTime? = null,
    val targetDate: LocalDateTime? = null,
    val priority: Int? = null,
    val projectId: ProjectId? = null,
    val parentTaskId: TaskId? = null,
    val assigneeId: UserId? = null,
)
