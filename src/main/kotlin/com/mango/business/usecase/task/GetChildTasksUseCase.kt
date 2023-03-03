package com.mango.business.usecase.task

import com.mango.business.model.Task
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetChildTasksUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskUseCase: GetTaskUseCase,
) {
    operator fun invoke(taskId: TaskId): List<Task> {
        getTaskUseCase(taskId)

        return taskRepository
            .tasks
            .filter { it.parentTaskId == taskId }
    }
}
