package com.mango.domain.project.usecase

import com.mango.domain.project.ProjectRepository
import org.springframework.stereotype.Service

@Service
class GetAllProjectsUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke() = projectRepository.projects
}
