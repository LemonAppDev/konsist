package com.mango.domain.task.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
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
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
) {
    operator fun invoke(taskId: TaskId): Task {
        val oldTask = getTaskOrThrowUseCase(taskId)
        return duplicate(oldTask, oldTask.parentTaskId)
    }

    private fun duplicate(oldTask: Task, parentTaskId: TaskId?): Task {
        val newTaskId = uuidFactory.createTaskId()
        val creationDate = localDateTimeFactory()
        val newTask = oldTask.copy(id = newTaskId, creationDate = creationDate, parentTaskId = parentTaskId)

        return taskRepository.saveTask(newTask).also {
            addTaskActivityUseCase(newTask.id, TaskActivityType.CREATE, newTask.creationDate)

            it.projectId?.let { projectId ->
                addProjectActivityUseCase(projectId, ProjectActivityType.TASK_ADDED, creationDate)
            }

            taskRepository.tasks
                .filter { task -> task.parentTaskId == oldTask.id }
                .forEach { task -> duplicate(task, it.id) }
        }
    }
}
