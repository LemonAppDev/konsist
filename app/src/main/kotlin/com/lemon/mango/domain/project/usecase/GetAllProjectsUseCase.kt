package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.project.ProjectRepository
import org.springframework.stereotype.Service

@Service
class GetAllProjectsUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke() = projectRepository.projects
}
