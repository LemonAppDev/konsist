package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateProjectNameUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId, newName: String, date: LocalDateTime) {
        val project = getProjectOrThrowUseCase(projectId)

        val oldName = project.name

        if (newName != oldName) {
            val newProject = project.copy(name = newName)

            projectRepository.saveProject(newProject)

            addProjectActivityUseCase(newProject.id, ProjectActivityType.UPDATE_NAME, date, newName, oldName)
        }
    }
}
