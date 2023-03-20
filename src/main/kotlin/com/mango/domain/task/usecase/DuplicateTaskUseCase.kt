package com.mango.domain.task.usecase

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DuplicateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
) {
    operator fun invoke(taskId: TaskId, projectId: ProjectId? = null, date: LocalDateTime? = null): Task {
        val oldTask = getTaskOrThrowUseCase(taskId)
        val newProjectId = projectId ?: oldTask.projectId
        return duplicate(oldTask, oldTask.parentTaskId, newProjectId, date)
    }

    private fun duplicate(oldTask: Task, parentTaskId: TaskId?, projectId: ProjectId?, date: LocalDateTime? = null): Task {
        val newTaskId = uuidFactory.createTaskId()
        val creationDate = date ?: localDateTimeFactory()
        val newTask = oldTask.copy(id = newTaskId, creationDate = creationDate, parentTaskId = parentTaskId, projectId = projectId)

        return saveTaskUseCase(newTask, creationDate).also {
            taskRepository.tasks
                .filter { task -> task.parentTaskId == oldTask.id }
                .forEach { task -> duplicate(task, it.id, it.projectId, date) }
        }
    }
}
