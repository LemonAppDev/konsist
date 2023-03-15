package com.mango.domain.project.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class DeleteProjectUseCase(
    private val projectRepository: ProjectRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskRepository: TaskRepository,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
) {
    operator fun invoke(projectId: ProjectId) {
        val project = projectRepository.getProject(projectId)

        project?.let {
            projectRepository.deleteProject(it)

            val date = localDateTimeFactory()
            addProjectActivityUseCase(it.id, ProjectActivityType.DELETE, date)

            taskRepository.tasks
                .filter { task -> task.projectId == projectId }
                .forEach { task ->
                    taskRepository.deleteTask(task)

                    addTaskActivityUseCase(task.id, TaskActivityType.DELETE, date)
                }
        }
    }
}
