package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
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
