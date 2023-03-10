package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivity
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CreateTaskActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
    ) = TaskActivity(userRepository.getCurrentUser().id, TaskActivityType.CREATE, taskId, date, null, null).also {
        activityRepository.addTaskActivity(it)
    }
}
