package com.mango.domain.project.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.activity.DeleteProjectActivityFactory
import com.mango.domain.project.model.ProjectId
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
