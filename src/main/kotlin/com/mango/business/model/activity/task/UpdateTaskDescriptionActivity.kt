package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskDescriptionActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: String?,
    val newValue: String?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskDescriptionActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: String?,
        newValue: String?,
    ) = UpdateTaskDescriptionActivity(taskId, date, oldValue, newValue)
}
