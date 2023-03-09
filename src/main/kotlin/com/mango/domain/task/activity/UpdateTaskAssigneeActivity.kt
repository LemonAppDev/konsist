package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskAssigneeActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: UserId?,
    val newValue: UserId?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskAssigneeActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: UserId?,
        newValue: UserId?,
    ) = UpdateTaskAssigneeActivity(taskId, date, oldValue, newValue)
}
