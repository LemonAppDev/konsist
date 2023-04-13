package com.lemon.mango.data.project

import com.lemon.mango.domain.project.model.Project
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
