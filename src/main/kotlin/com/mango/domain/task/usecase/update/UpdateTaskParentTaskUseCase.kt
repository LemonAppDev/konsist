package com.mango.domain.task.usecase.update

import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskParentTaskUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newParentTaskId: TaskId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        getTaskOrThrowUseCase(newParentTaskId)

        val oldParentTaskId = task.parentTaskId

        if (newParentTaskId != oldParentTaskId) {
            val newTask = task.copy(parentTaskId = newParentTaskId)

            taskRepository.saveTask(newTask)

            updateTaskActivityUseCase(
                newTask.value,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId?.value.toString(),
                TaskActivityType.UPDATE_PARENT_TASK,
            )
        }
    }
}
