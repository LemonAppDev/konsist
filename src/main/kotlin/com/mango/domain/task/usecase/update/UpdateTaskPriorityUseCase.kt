package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskPriorityUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newPriority: Priority, date: LocalDateTime) {
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
