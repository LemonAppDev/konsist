package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDescriptionUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId, newDescription: String?, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldDescription = task.description

        if (newDescription != oldDescription) {
            val newTask = task.copy(description = newDescription)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_DESCRIPTION,
                date,
                newDescription,
                oldDescription,
            )
        }
    }
}
