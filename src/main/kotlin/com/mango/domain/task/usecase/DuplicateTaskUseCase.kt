package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
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
    private val taskActivityFactory: TaskActivityFactory,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId): Task {
        val oldTask = getTaskOrThrowUseCase(taskId)

        val newTaskId = uuidFactory.createTaskId()
        val creationDate = localDateTimeFactory()
        val newTask = oldTask.copy(id = newTaskId, creationDate = creationDate)

        return taskRepository.saveTask(newTask).also {
            val activity = taskActivityFactory(newTask.id, newTask.creationDate, TaskActivityType.CREATE)
            activityRepository.addTaskActivity(activity)

            it.projectId?.let { projectId ->
                addProjectActivityUseCase(projectId, ProjectActivityType.TASK_ADDED, creationDate)
            }
        }
    }
}
