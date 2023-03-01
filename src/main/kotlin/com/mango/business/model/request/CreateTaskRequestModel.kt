package com.mango.business.model.request

import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
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
