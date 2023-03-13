package com.mango.domain.activity.model

import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class TaskActivity(
    val id: TaskActivityId,
    val userId: UserId,
    val type: TaskActivityType,
    val taskId: TaskId,
    val date: LocalDateTime,
    val newValue: String?,
    val oldValue: String?,
)
