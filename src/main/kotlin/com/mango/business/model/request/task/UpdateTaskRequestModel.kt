package com.mango.business.model.request.task

import com.mango.business.model.Priority
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
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
    val isCompleted: Boolean = false,
)
