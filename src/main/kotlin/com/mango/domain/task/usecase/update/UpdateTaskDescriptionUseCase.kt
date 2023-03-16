package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDescriptionUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
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
