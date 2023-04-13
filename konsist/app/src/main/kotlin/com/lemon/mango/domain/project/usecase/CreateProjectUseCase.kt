package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.ProjectFactory
import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.Project
import org.springframework.stereotype.Service

@Service
class CreateProjectUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val checkNewProjectNameUseCase: CheckNewProjectNameUseCase,
    private val projectFactory: ProjectFactory,
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(name: String, color: Color?, isFavourite: Boolean?): Project {
        checkNewProjectNameUseCase(name)
        val project = projectFactory(name, color, isFavourite)

        return projectRepository.saveProject(project).also {
            addProjectActivityUseCase(project.id, ProjectActivityType.CREATE, project.creationDate)
        }
    }
}
