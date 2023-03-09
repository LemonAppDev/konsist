package com.mango.domain.task.activity

import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.TaskId
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
