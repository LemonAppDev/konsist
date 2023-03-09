package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class GetChildTasksUseCase(
    private val taskRepository: TaskRepository,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
) {
    operator fun invoke(taskId: TaskId): List<Task> {
        checkTaskIdUseCase(taskId)

        return taskRepository
            .tasks
            .filter { it.parentTaskId == taskId }
    }
}
