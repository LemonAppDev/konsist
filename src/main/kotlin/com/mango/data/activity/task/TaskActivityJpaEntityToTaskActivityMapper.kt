package com.mango.data.activity.task

import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityId
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class TaskActivityJpaEntityToTaskActivityMapper {
    operator fun invoke(taskActivityJpaEntity: TaskActivityJpaEntity) = TaskActivity(
        id = TaskActivityId(taskActivityJpaEntity.id),
        userId = UserId(taskActivityJpaEntity.userId),
        type = TaskActivityType.getByValue(taskActivityJpaEntity.type),
        taskId = TaskId(taskActivityJpaEntity.taskId),
        date = taskActivityJpaEntity.date,
        newValue = taskActivityJpaEntity.newValue,
        oldValue = taskActivityJpaEntity.oldValue,
    )
}
