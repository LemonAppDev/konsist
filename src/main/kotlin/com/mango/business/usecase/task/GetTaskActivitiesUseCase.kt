package com.mango.business.usecase.task

import com.mango.business.model.activity.task.TaskActivity
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
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
