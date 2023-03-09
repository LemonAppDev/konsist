package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskPriorityActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: com.mango.domain.task.model.Priority?,
    val newValue: com.mango.domain.task.model.Priority?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskPriorityActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: com.mango.domain.task.model.Priority?,
        newValue: com.mango.domain.task.model.Priority?,
    ) = UpdateTaskPriorityActivity(taskId, date, oldValue, newValue)
}
