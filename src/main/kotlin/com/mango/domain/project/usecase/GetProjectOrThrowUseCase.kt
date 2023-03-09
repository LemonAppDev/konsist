package com.mango.domain.project.usecase

import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import org.springframework.stereotype.Service

@Service
class GetProjectOrThrowUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId): Project {
        val project = projectRepository.getProject(projectId)
        requireNotNull(project) { "Project with id: $projectId doesn't exist" }
        return project
    }
}
