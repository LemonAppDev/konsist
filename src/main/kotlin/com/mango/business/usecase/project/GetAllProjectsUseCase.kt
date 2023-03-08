package com.mango.business.usecase.project

import com.mango.persistence.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class GetAllProjectsUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke() = projectRepository.projects
}
