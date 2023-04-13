package com.lemon.mango.data.activity.task

import com.lemon.mango.domain.activity.model.TaskActivity
import com.lemon.mango.domain.activity.model.TaskActivityId
import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
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
