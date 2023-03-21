package com.lemon.mango.data.activity.project

import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.activity.model.ProjectActivityId
import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.user.model.UserId
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
