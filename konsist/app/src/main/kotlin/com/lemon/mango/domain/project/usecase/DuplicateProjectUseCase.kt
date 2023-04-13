package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.usecase.DuplicateTaskUseCase
import com.lemon.mango.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class DuplicateProjectUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val checkNewProjectNameUseCase: CheckNewProjectNameUseCase,
    private val duplicateTaskUseCase: DuplicateTaskUseCase,
    private val getNewProjectNameUseCase: GetNewProjectNameUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val uuidFactory: UuidFactory,
) {
    operator fun invoke(projectId: ProjectId, duplicatedProjectName: String? = null): Project {
        val oldProject = getProjectOrThrowUseCase(projectId)

        val newProjectId = uuidFactory.createProjectId()
        val creationDate = localDateTimeFactory()

        val newName = if (duplicatedProjectName != null) {
            checkNewProjectNameUseCase(duplicatedProjectName)
            duplicatedProjectName
        } else {
            getNewProjectNameUseCase(oldProject.name)
        }

        val newProject = oldProject.copy(
            id = newProjectId,
            name = newName,
            creationDate = creationDate,
            owner = userRepository.getCurrentUser().id,
        )

        return projectRepository.saveProject(newProject).also {
            addProjectActivityUseCase(newProjectId, ProjectActivityType.CREATE, creationDate)

            taskRepository.tasks
                .filter { task -> task.projectId == projectId && task.parentTaskId == null }
                .forEach { task -> duplicateTaskUseCase(task.id, newProjectId, creationDate) }
        }
    }
}
