package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.activity.ActivityRepository
import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.project.model.ProjectId
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
