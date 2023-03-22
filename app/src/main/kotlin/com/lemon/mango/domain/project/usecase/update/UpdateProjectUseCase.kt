package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import org.springframework.stereotype.Service

@Service
class UpdateProjectUseCase(
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val updateProjectColorUseCase: UpdateProjectColorUseCase,
    private val updateProjectIsFavouriteUseCase: UpdateProjectIsFavouriteUseCase,
    private val updateProjectNameUseCase: UpdateProjectNameUseCase,
) {
    operator fun invoke(projectId: ProjectId, name: String, color: Color, isFavourite: Boolean) {
        val project = getProjectOrThrowUseCase(projectId)

        // Single date allows to potentially group changes performed at the same time
        val date = localDateTimeFactory()

        updateProjectNameUseCase(project.id, name, date)
        updateProjectColorUseCase(project.id, color, date)
        updateProjectIsFavouriteUseCase(project.id, isFavourite, date)
    }
}
