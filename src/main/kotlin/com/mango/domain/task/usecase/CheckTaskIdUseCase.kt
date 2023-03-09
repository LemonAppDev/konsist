package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepository
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class CheckTaskIdUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId) {
        check(taskRepository.containsTask(taskId)) { "Task with id: $taskId doesn't exist" }
    }
}
