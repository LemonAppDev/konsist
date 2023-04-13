package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Priority
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskPriorityUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId, newPriority: Priority?, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldPriority = task.priority

        if (newPriority != oldPriority) {
            val newTask = task.copy(priority = newPriority)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_PRIORITY,
                date,
                newPriority.toString(),
                oldPriority.toString(),
            )
        }
    }
}
