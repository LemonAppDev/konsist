package com.mango.domain.project.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.common.model.Color
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.Project
import org.springframework.stereotype.Service

@Service
class CreateProjectUseCase(
    private val projectFactory: ProjectFactory,
    private val projectRepository: ProjectRepository,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val checkNewProjectNameUseCase: CheckNewProjectNameUseCase,
) {
    operator fun invoke(name: String, color: Color?, isFavourite: Boolean?): Project {
        checkNewProjectNameUseCase(name)
        val project = projectFactory(name, color, isFavourite)

        return projectRepository.saveProject(project).also {
            addProjectActivityUseCase(project.id, ProjectActivityType.CREATE, project.creationDate)
        }
    }
}
