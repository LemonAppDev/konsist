package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
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
