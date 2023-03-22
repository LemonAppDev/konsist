package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateProjectColorUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId, newColor: Color, date: LocalDateTime) {
        val project = getProjectOrThrowUseCase(projectId)

        val oldColor = project.color

        if (newColor != oldColor) {
            val newProject = project.copy(color = newColor)

            projectRepository.saveProject(newProject)

            addProjectActivityUseCase(newProject.id, ProjectActivityType.UPDATE_COLOR, date, newColor.value, oldColor.value)
        }
    }
}
