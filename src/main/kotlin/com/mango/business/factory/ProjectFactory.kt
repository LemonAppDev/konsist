package com.mango.business.factory

import com.mango.business.model.Project
import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.Color
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
    ): Project {
        return Project(
            uuidFactory.createProjectId(),
            userRepository.getCurrentUser().id,
            localDateTimeFactory(),
            createProjectRequestModel.name,
            createProjectRequestModel.color ?: DEFAULT_PROJECT_COLOR,
            createProjectRequestModel.isFavourite ?: false,
            createProjectRequestModel.collaborators,
        )
    }

    companion object {
        private val DEFAULT_PROJECT_COLOR = Color("#666666")
    }
}
