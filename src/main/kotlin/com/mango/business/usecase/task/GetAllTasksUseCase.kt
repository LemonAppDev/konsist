package com.mango.business.usecase.task

import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetAllTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke() = taskRepository.tasks
}
