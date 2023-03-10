package com.mango.domain.task.usecase.update

import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDescriptionUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newDescription: String, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldDescription = task.description

        if (newDescription != oldDescription) {
            val newTask = task.copy(description = newDescription)

            taskRepository.saveTask(newTask)

            updateTaskActivityUseCase(newTask.value, date, newDescription, oldDescription, TaskActivityType.UPDATE_DESCRIPTION)
        }
    }
}
