package com.mango.business.usecase.task

import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetChildTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId) = taskRepository
        .tasks
        .filter { it.parentTaskId == taskId }
}
