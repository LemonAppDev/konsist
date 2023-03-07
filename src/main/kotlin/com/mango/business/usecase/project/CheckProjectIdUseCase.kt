package com.mango.business.usecase.project

import com.mango.business.model.value.ProjectId
import com.mango.persistence.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class CheckProjectIdUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId) {
        check(projectRepository.containsProject(projectId)) { "Project with id: $projectId doesn't exist" }
    }
}
