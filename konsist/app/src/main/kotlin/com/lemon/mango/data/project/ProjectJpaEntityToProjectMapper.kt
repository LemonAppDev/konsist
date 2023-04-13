package com.lemon.mango.data.project

import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class ProjectJpaEntityToProjectMapper {
    operator fun invoke(projectJpaEntity: ProjectJpaEntity) = Project(
        id = ProjectId(projectJpaEntity.id),
        owner = UserId(projectJpaEntity.ownerId),
        creationDate = projectJpaEntity.creationDate,
        name = projectJpaEntity.name,
        color = projectJpaEntity.color,
        isFavourite = projectJpaEntity.isFavourite,
    )
}
