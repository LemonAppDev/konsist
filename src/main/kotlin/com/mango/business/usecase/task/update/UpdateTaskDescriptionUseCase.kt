package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskDescriptionActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDescriptionUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskDescriptionActivityFactory: UpdateTaskDescriptionActivityFactory,
    private val getTaskUseCase: GetTaskUseCase,
) {
    operator fun invoke(taskId: TaskId, newDescription: String, date: LocalDateTime) {
        val task = getTaskUseCase(taskId)

        val oldDescription = task.description
        val newTask = task.copy(description = newDescription)

        taskRepository.updateTask(newTask)

        val activity = updateTaskDescriptionActivityFactory(newTask.id, date, oldDescription, newDescription)
        activityRepository.addActivity(activity)
    }
}
