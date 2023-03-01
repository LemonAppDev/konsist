package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskNameActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: String,
    val newValue: String,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskNameActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: String,
        newValue: String,
    ) = UpdateTaskNameActivity(taskId, date, oldValue, newValue)
}
