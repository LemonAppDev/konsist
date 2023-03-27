package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DuplicateTaskUseCase(
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val uuidFactory: UuidFactory,
) {
    operator fun invoke(taskId: TaskId, projectId: ProjectId? = null, date: LocalDateTime? = null): Task {
        val oldTask = getTaskOrThrowUseCase(taskId)
        val newProjectId = projectId ?: oldTask.projectId
        return duplicate(oldTask, oldTask.parentTaskId, newProjectId, date)
    }

    private fun duplicate(oldTask: Task, parentTaskId: TaskId?, projectId: ProjectId?, date: LocalDateTime? = null): Task {
        val newTaskId = uuidFactory.createTaskId()
        val creationDate = date ?: localDateTimeFactory()
        val newTask = oldTask.copy(
            id = newTaskId,
            creationDate = creationDate,
            parentTaskId = parentTaskId,
            projectId = projectId,
            ownerId = userRepository.getCurrentUser().id,
        )

        return saveTaskUseCase(newTask, creationDate).also {
            taskRepository.tasks
                .filter { task -> task.parentTaskId == oldTask.id }
                .forEach { task -> duplicate(task, it.id, it.projectId, date) }
        }
    }
}
