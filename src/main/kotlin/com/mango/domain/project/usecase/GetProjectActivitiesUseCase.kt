package com.mango.domain.project.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.project.model.ProjectId
import org.springframework.stereotype.Service

@Service
class GetProjectActivitiesUseCase(
    private val activityRepository: ActivityRepository,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
) {
    operator fun invoke(projectId: ProjectId): List<ProjectActivity> {
        checkProjectIdUseCase(projectId)

        return activityRepository
            .projectActivities
            .filter { it.projectId == projectId }
    }
}
