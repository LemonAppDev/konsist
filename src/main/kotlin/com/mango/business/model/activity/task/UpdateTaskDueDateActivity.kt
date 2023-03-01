package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskDueDateActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: LocalDateTime?,
    val newValue: LocalDateTime?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskDueDateActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: LocalDateTime?,
        newValue: LocalDateTime?,
    ) = UpdateTaskDueDateActivity(taskId, date, oldValue, newValue)
}
