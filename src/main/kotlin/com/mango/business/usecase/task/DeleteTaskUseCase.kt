package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.activity.task.DeleteTaskActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
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
