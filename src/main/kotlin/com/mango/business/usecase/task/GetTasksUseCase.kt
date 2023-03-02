package com.mango.business.usecase.task

import com.mango.business.model.Task
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId): Task {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        return task
    }
}
