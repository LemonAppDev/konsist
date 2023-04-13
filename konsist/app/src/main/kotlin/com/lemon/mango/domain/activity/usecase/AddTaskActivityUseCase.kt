package com.lemon.mango.domain.activity.usecase

import com.lemon.mango.domain.activity.ActivityRepository
import com.lemon.mango.domain.activity.model.TaskActivity
import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AddTaskActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UuidFactory,
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
