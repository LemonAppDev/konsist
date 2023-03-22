package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateProjectIsFavouriteUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(projectId: ProjectId, isFavourite: Boolean, date: LocalDateTime) {
        val project = getProjectOrThrowUseCase(projectId)

        if (isFavourite != project.isFavourite) {
            val newProject = project.copy(isFavourite = isFavourite)

            projectRepository.saveProject(newProject)

            addProjectActivityUseCase(
                newProject.id,
                ProjectActivityType.UPDATE_IS_FAVOURITE,
                date,
                isFavourite.toString(),
                project.isFavourite.toString(),
            )
        }
    }
}
