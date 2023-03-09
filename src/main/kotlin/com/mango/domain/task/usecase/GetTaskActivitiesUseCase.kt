package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.task.activity.TaskActivity
import com.mango.domain.task.model.TaskId
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
