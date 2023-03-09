package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.task.activity.UpdateTaskDescriptionActivityFactory
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
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
