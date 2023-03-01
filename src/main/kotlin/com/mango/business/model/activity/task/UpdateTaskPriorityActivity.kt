package com.mango.business.model.activity.task

import com.mango.business.model.Priority
import com.mango.business.model.value.TaskId
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
