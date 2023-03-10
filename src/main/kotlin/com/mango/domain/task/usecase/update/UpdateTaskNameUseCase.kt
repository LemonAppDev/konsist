package com.mango.domain.task.usecase.update

import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskNameUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newName: String, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldName = task.name

        if (newName != oldName) {
            val newTask = task.copy(name = newName)

            taskRepository.saveTask(newTask)

            updateTaskActivityUseCase(newTask.value, date, newName, oldName, TaskActivityType.UPDATE_NAME)
        }
    }
}
