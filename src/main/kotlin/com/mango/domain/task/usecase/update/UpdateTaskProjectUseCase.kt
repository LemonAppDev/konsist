package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
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
    operator fun invoke(taskId: TaskId, newProjectId: ProjectId?, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)
        newProjectId?.let { getProjectOrThrowUseCase(it) }

        updateTaskProject(task, newProjectId, date)
    }

    private fun updateTaskProject(task: Task, newProjectId: ProjectId?, date: LocalDateTime) {
        val oldProjectId = task.projectId

        if (newProjectId != oldProjectId) {
            val newTask = task.copy(projectId = newProjectId)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_PROJECT,
                date,
                newProjectId?.value.toString(),
                oldProjectId?.value.toString(),
            )
            oldProjectId?.let { addProjectActivityUseCase(it, ProjectActivityType.TASK_REMOVED, date) }
            newProjectId?.let { addProjectActivityUseCase(it, ProjectActivityType.TASK_ADDED, date) }

            taskRepository.tasks
                .filter { it.parentTaskId == task.id }
                .forEach { updateTaskProject(it, newProjectId, date) }
        }
    }
}
