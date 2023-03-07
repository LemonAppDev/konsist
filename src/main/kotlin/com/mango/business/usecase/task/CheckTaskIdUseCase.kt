package com.mango.business.usecase.task

import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class CheckTaskIdUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId) {
        check(taskRepository.containsTask(taskId)) { "Task with id: $taskId doesn't exist" }
    }
}
