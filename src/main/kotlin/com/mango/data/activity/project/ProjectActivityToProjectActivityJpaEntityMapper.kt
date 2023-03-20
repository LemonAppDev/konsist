package com.mango.data.activity.project

import com.mango.domain.activity.model.ProjectActivity
import org.springframework.stereotype.Service

@Service
class ProjectActivityToProjectActivityJpaEntityMapper {
    operator fun invoke(projectActivity: ProjectActivity) = ProjectActivityJpaEntity(
        id = projectActivity.id.value,
        userId = projectActivity.userId.value,
        type = projectActivity.type.value,
        projectId = projectActivity.projectId.value,
        date = projectActivity.date,
        newValue = projectActivity.newValue,
        oldValue = projectActivity.oldValue,
    )
}
