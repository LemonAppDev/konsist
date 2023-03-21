package com.mango.domain.task.usecase

import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class GetChildTasksUseCase(
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId): List<Task> {
        checkTaskIdUseCase(taskId)

        return taskRepository
            .tasks
            .filter { it.parentTaskId == taskId }
    }
}
