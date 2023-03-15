package com.mango.domain.project.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
import org.springframework.stereotype.Service

@Service
class CreateProjectUseCase(
    private val projectFactory: ProjectFactory,
    private val projectRepository: ProjectRepository,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
) {
    operator fun invoke(createProjectRequestModel: CreateProjectRequestModel): Project {
        val project = projectFactory(createProjectRequestModel)

        return projectRepository.saveProject(project).also {
            addProjectActivityUseCase(project.id, ProjectActivityType.CREATE, project.creationDate)
        }
    }
}
