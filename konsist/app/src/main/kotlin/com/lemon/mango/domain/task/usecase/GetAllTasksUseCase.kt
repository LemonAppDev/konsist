package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetAllTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke() = taskRepository.tasks
}
