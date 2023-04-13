package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.ProjectId
import org.springframework.stereotype.Service

@Service
class CheckProjectIdUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId) {
        check(projectRepository.containsProject(projectId)) { "Project with id: $projectId doesn't exist" }
    }
}
