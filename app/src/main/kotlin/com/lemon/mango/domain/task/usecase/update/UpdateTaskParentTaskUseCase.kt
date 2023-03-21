package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskParentTaskUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId, newParentTaskId: TaskId?, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)
        val parentTask = newParentTaskId?.let { getTaskOrThrowUseCase(it) }

        val oldParentTaskId = task.parentTaskId

        if (newParentTaskId != oldParentTaskId) {
            require(parentTask?.projectId == task.projectId) { "Task and parent task are not in the same project" }
            val newTask = task.copy(parentTaskId = newParentTaskId)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_PARENT_TASK,
                date,
                newParentTaskId?.value.toString(),
                oldParentTaskId?.value.toString(),
            )
        }
    }
}
