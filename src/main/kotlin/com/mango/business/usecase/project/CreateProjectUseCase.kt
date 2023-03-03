package com.mango.business.usecase.project

import com.mango.business.factory.ProjectFactory
import com.mango.business.model.Project
import com.mango.business.model.activity.project.CreateProjectActivityFactory
import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
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

        projectRepository.addProject(project)

        val activity = createProjectActivityFactory(project.id, project.creationDate)
        activityRepository.addActivity(activity)

        return project
    }
}
