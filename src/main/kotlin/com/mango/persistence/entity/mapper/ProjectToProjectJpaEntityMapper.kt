package com.mango.persistence.entity.mapper

import com.mango.business.model.Project
import com.mango.persistence.entity.ProjectJpaEntity
import org.springframework.stereotype.Service

@Service
class ProjectToProjectJpaEntityMapper {
    operator fun invoke(project: Project) = ProjectJpaEntity(
        id = project.id.value,
        ownerId = project.owner.value,
        creationDate = project.creationDate,
        name = project.name,
        color = project.color,
        isFavourite = project.isFavourite,
    )
}
