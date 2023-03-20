package com.mango.domain.task.usecase

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class DuplicateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
) {
    operator fun invoke(taskId: TaskId): Task {
        val oldTask = getTaskOrThrowUseCase(taskId)
        return duplicate(oldTask, oldTask.parentTaskId)
    }

    private fun duplicate(oldTask: Task, parentTaskId: TaskId?): Task {
        val newTaskId = uuidFactory.createTaskId()
        val creationDate = localDateTimeFactory()
        val newTask = oldTask.copy(id = newTaskId, creationDate = creationDate, parentTaskId = parentTaskId)

        return saveTaskUseCase(newTask, creationDate).also {
            taskRepository.tasks
                .filter { task -> task.parentTaskId == oldTask.id }
                .forEach { task -> duplicate(task, it.id) }
        }
    }
}
