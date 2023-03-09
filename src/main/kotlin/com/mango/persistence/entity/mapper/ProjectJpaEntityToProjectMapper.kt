package com.mango.persistence.entity.mapper

import com.mango.business.model.Project
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.ProjectJpaEntity
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
