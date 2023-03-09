package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class CreateTaskActivity(
    taskId: TaskId,
    date: LocalDateTime,
) : TaskActivity(taskId, date)

@Service
class CreateTaskActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
    ) = CreateTaskActivity(taskId, date)
}
