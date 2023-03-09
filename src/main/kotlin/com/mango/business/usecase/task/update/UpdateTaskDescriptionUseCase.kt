package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskDescriptionActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDescriptionUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskDescriptionActivityFactory: UpdateTaskDescriptionActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(taskId: TaskId, newDescription: String, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldDescription = task.description

        if (newDescription != oldDescription) {
            val newTask = task.copy(description = newDescription)

            taskRepository.saveTask(newTask)

            val activity = updateTaskDescriptionActivityFactory(newTask.id, date, oldDescription, newDescription)
            activityRepository.addActivity(activity)
        }
    }
}
