package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.activity.ActivityRepository
import com.lemon.mango.domain.activity.model.TaskActivity
import com.lemon.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class GetTaskActivitiesUseCase(
    private val activityRepository: ActivityRepository,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
) {
    operator fun invoke(taskId: TaskId): List<TaskActivity> {
        checkTaskIdUseCase(taskId)

        return activityRepository
            .taskActivities
            .filter { it.taskId == taskId }
    }
}
