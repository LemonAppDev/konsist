package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskCompleteDateActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: LocalDateTime?,
    val newValue: LocalDateTime?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskCompleteDateActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: LocalDateTime?,
        newValue: LocalDateTime?,
    ) = UpdateTaskCompleteDateActivity(taskId, date, oldValue, newValue)
}
