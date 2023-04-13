package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class CheckTaskIdUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId) {
        check(taskRepository.containsTask(taskId)) { "Task with id: $taskId doesn't exist" }
    }
}
