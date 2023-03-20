package com.mango.data.activity.project

import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityId
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class ProjectActivityJpaEntityToProjectActivityMapper {
    operator fun invoke(projectActivityJpaEntity: ProjectActivityJpaEntity) = ProjectActivity(
        id = ProjectActivityId(projectActivityJpaEntity.id),
        userId = UserId(projectActivityJpaEntity.userId),
        type = ProjectActivityType.getByValue(projectActivityJpaEntity.type),
        projectId = ProjectId(projectActivityJpaEntity.projectId),
        date = projectActivityJpaEntity.date,
        newValue = projectActivityJpaEntity.newValue,
        oldValue = projectActivityJpaEntity.oldValue,
    )
}
