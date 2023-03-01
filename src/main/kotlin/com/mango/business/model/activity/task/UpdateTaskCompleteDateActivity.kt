package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskCompleteDateActivity(
    taskId: TaskId,
    date: LocalDateTime,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskCompleteDateActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
    ) = UpdateTaskCompleteDateActivity(taskId, date)
}
