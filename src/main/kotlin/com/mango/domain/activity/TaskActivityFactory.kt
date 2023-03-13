package com.mango.domain.activity

import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.UUIDFactory
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskActivityFactory(
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        type: TaskActivityType,
        newValue: String? = null,
        oldValue: String? = null,
    ) = TaskActivity(
        uuidFactory.createTaskActivityId(),
        userRepository.getCurrentUser().id,
        type,
        taskId,
        date,
        newValue,
        oldValue,
    )
}
