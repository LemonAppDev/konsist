package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class GetTaskOrThrowUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId): Task {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }
        return task
    }
}
