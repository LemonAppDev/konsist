package com.mango.domain.project.usecase

import com.mango.data.activity.ActivityRepository
import com.mango.data.project.ProjectRepository
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.activity.CreateProjectActivityFactory
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
import org.springframework.stereotype.Service

@Service
class CreateProjectUseCase(
    private val projectFactory: ProjectFactory,
    private val projectRepository: ProjectRepository,
    private val createProjectActivityFactory: CreateProjectActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(createProjectRequestModel: CreateProjectRequestModel): Project {
        val project = projectFactory(createProjectRequestModel)

        return projectRepository.saveProject(project).also {
            val activity = createProjectActivityFactory(project.id, project.creationDate)
            activityRepository.addActivity(activity)
        }
    }
}
