package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetAllTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke() = taskRepository.tasks
}
