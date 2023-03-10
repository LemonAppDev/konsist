package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivity
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        newValue: String?,
        oldValue: String?,
        type: TaskActivityType,
    ) = TaskActivity(
        userRepository.getCurrentUser().id,
        type,
        taskId,
        date,
        newValue,
        oldValue,
    ).also {
        activityRepository.addTaskActivity(it)
    }
}
