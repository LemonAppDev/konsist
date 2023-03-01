package com.mango.business.model.activity.task

import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class UpdateTaskProjectActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val oldValue: ProjectId?,
    val newValue: ProjectId?,
) : TaskActivity(taskId, date)

@Service
class UpdateTaskProjectActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        oldValue: ProjectId?,
        newValue: ProjectId?,
    ) = UpdateTaskProjectActivity(taskId, date, oldValue, newValue)
}
