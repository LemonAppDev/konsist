package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.activity.DeleteTaskActivityFactory
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class DeleteTaskUseCase(
    private val taskRepository: TaskRepository,
    private val deleteTaskActivityFactory: DeleteTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(taskId: TaskId) {
        val task = taskRepository.getTask(taskId)

        task?.let {
            taskRepository.deleteTask(it)

            val date = localDateTimeFactory()
            val activity = deleteTaskActivityFactory(it.id, date)
            activityRepository.addActivity(activity)
        }
    }
}
