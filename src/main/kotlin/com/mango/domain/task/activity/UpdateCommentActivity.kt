package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateCommentActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: String,
    val newValue: String?,
) : TaskActivity(taskId, date)

@Service
class UpdateCommentActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: String,
        newValue: String?,
    ) = UpdateCommentActivity(taskId, date, oldValue, newValue)
}
