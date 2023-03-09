package com.mango.domain.task.activity

import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskPriorityActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: Priority?,
    val newValue: Priority?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskPriorityActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: Priority?,
        newValue: Priority?,
    ) = UpdateTaskPriorityActivity(taskId, date, oldValue, newValue)
}
