package com.mango.business.usecase.task

import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import org.springframework.stereotype.Service

@Service
class GetTaskActivitiesUseCase(
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId) = activityRepository
        .activities
        .filter { it.taskId == taskId }
}
