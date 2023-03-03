package com.mango.business.factory

import com.mango.business.model.Project
import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ProjectFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,

) {
    operator fun invoke(
        createProjectRequestModel: CreateProjectRequestModel,
    ) = Project(
        uuidFactory.createProjectId(),
        userRepository.getCurrentUser().id,
        localDateTimeFactory(),
        createProjectRequestModel.name,
        createProjectRequestModel.collaborators,
        createProjectRequestModel.isFavourite,
        createProjectRequestModel.color,
    )
}
