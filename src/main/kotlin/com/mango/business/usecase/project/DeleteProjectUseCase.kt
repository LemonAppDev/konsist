package com.mango.business.usecase.project

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.activity.project.DeleteProjectActivityFactory
import com.mango.business.model.value.ProjectId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class DeleteProjectUseCase(
    private val projectRepository: ProjectRepository,
    private val deleteProjectActivityFactory: DeleteProjectActivityFactory,
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(projectId: ProjectId) {
        val project = projectRepository.getProject(projectId)

        project?.let {
            projectRepository.deleteProject(it)

            val date = localDateTimeFactory()
            val activity = deleteProjectActivityFactory(it.id, date)
            activityRepository.addActivity(activity)
        }
    }
}
