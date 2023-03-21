package com.mango.domain.task.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SaveTaskUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(task: Task, date: LocalDateTime? = null) =
        taskRepository.saveTask(task).also {
            val activityDate = date ?: localDateTimeFactory()
            addTaskActivityUseCase(it.id, TaskActivityType.CREATE, activityDate)

            it.projectId?.let { projectId ->
                addProjectActivityUseCase(projectId, ProjectActivityType.TASK_ADDED, activityDate)
            }
        }
}
