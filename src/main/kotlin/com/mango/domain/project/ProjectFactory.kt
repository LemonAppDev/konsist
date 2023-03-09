package com.mango.domain.project

import com.mango.data.user.UserRepository
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
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
        )
    }

    companion object {
        private val DEFAULT_PROJECT_COLOR = Color("#666666")
    }
}
