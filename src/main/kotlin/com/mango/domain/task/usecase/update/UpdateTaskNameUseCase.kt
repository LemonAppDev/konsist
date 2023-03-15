package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskNameUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newName: String, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldName = task.name

        if (newName != oldName) {
            val newTask = task.copy(name = newName)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(newTask.id, TaskActivityType.UPDATE_NAME, date, newName, oldName)
        }
    }
}
