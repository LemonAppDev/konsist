package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskParentTaskActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: TaskId?,
    val newValue: TaskId?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskParentTaskActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: TaskId?,
        newValue: TaskId?,
    ) = UpdateTaskParentTaskActivity(taskId, date, oldValue, newValue)
}
