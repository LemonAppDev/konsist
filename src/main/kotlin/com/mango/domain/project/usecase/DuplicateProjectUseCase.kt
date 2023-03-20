package com.mango.domain.project.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.usecase.DuplicateTaskUseCase
import org.springframework.stereotype.Service

@Service
class DuplicateProjectUseCase(
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val projectRepository: ProjectRepository,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val taskRepository: TaskRepository,
    private val duplicateTaskUseCase: DuplicateTaskUseCase,
) {
    operator fun invoke(projectId: ProjectId): Project {
        val oldProject = getProjectOrThrowUseCase(projectId)

        val newProjectId = uuidFactory.createProjectId()
        val creationDate = localDateTimeFactory()
        val newProject = oldProject.copy(id = newProjectId, creationDate = creationDate)

        return projectRepository.saveProject(newProject).also {
            addProjectActivityUseCase(newProjectId, ProjectActivityType.CREATE, creationDate)

            taskRepository.tasks
                .filter { task -> task.projectId == projectId }
                .forEach { task -> duplicateTaskUseCase(task.id, newProjectId, creationDate) }
        }
    }
}
