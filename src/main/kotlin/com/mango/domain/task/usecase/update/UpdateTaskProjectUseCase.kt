package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskProjectUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newProjectId: ProjectId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)
        getProjectOrThrowUseCase(newProjectId)

        val oldProjectId = task.projectId

        if (newProjectId != oldProjectId) {
            val newTask = task.copy(projectId = newProjectId)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_PROJECT,
                date,
                newProjectId.value.toString(),
                oldProjectId?.value.toString(),
            )

            taskRepository.tasks
                .filter { it.parentTaskId == taskId }
                .forEach {
                    taskRepository.saveTask(it.copy(projectId = newProjectId))

                    addTaskActivityUseCase(
                        it.id,
                        TaskActivityType.UPDATE_PROJECT,
                        date,
                        newProjectId.value.toString(),
                        oldProjectId?.value.toString(),
                    )
                }

            addProjectActivityUseCase(newProjectId, ProjectActivityType.TASK_ADDED, date)
        }
    }
}
