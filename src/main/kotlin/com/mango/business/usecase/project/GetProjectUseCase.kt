package com.mango.business.usecase.project

import com.mango.business.model.Project
import com.mango.business.model.value.ProjectId
import com.mango.persistence.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class GetProjectUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId): Project {
        val project = projectRepository.getProject(projectId)
        requireNotNull(project) { "Project with id: $projectId doesn't exist" }

        return project
    }
}
