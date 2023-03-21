package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AddTaskActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        taskId: TaskId,
        type: TaskActivityType,
        date: LocalDateTime? = null,
        newValue: String? = null,
        oldValue: String? = null,
    ) {
        val activity = TaskActivity(
            uuidFactory.createTaskActivityId(),
            userRepository.getCurrentUser().id,
            type,
            taskId,
            date ?: localDateTimeFactory(),
            newValue,
            oldValue,
        )
        activityRepository.addTaskActivity(activity)
    }
}
