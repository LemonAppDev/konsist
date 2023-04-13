package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.project.ProjectRepository
import org.springframework.stereotype.Service

@Service
class CheckNewProjectNameUseCase(
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(name: String) {
        require(name.isNotBlank()) { "Project name is blank: $name" }
        require(projectRepository.projects.none { it.name == name }) { "Project with name: $name already exists" }
    }
}
