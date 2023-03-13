package com.mango.data.activity.task

import com.mango.domain.activity.model.TaskActivity
import org.springframework.stereotype.Service

@Service
class TaskActivityToTaskActivityJpaEntityMapper {
    operator fun invoke(taskActivity: TaskActivity) = TaskActivityJpaEntity(
        id = taskActivity.id.value,
        userId = taskActivity.userId.value,
        type = taskActivity.type.value,
        taskId = taskActivity.taskId.value,
        date = taskActivity.date,
        newValue = taskActivity.newValue,
        oldValue = taskActivity.oldValue,
    )
}
