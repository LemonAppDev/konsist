package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
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
