package com.mango.domain.project.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.usecase.DeleteTaskUseCase
import org.springframework.stereotype.Service

@Service
class DeleteProjectUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(projectId: ProjectId) {
        val project = projectRepository.getProject(projectId)

        project?.let {
            projectRepository.deleteProject(it)

            val date = localDateTimeFactory()
            addProjectActivityUseCase(it.id, ProjectActivityType.DELETE, date)

            taskRepository.tasks
                .filter { task -> task.projectId == projectId }
                .forEach { task -> deleteTaskUseCase(task.id, date) }
        }
    }
}
