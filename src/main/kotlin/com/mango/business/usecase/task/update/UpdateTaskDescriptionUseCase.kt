package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskDescriptionActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDescriptionUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskDescriptionActivityFactory: UpdateTaskDescriptionActivityFactory,
) {
    operator fun invoke(taskId: TaskId, newDescription: String, date: LocalDateTime) {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        val oldDescription = task.description
        val newTask = task.copy(description = newDescription)

        taskRepository.updateTask(newTask)

        val activity = updateTaskDescriptionActivityFactory(newTask.id, date, oldDescription, newDescription)
        activityRepository.addActivity(activity)
    }
}
