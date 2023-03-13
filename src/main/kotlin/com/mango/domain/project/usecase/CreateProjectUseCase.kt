package com.mango.domain.project.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.ProjectActivityFactory
import com.mango.domain.activity.ProjectActivityType
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
import org.springframework.stereotype.Service

@Service
class CreateProjectUseCase(
    private val projectFactory: ProjectFactory,
    private val projectRepository: ProjectRepository,
    private val projectActivityFactory: ProjectActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(createProjectRequestModel: CreateProjectRequestModel): Project {
        val project = projectFactory(createProjectRequestModel)

        return projectRepository.saveProject(project).also {
            val activity = projectActivityFactory(project.id, project.creationDate, ProjectActivityType.CREATE)
            activityRepository.addProjectActivity(activity)
        }
    }
}
