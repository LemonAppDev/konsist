package com.mango.domain.project

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class ProjectFactory(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,

) {
    operator fun invoke(
        name: String,
        color: Color? = null,
        isFavourite: Boolean? = null,
    ): Project {
        return Project(
            uuidFactory.createProjectId(),
            userRepository.getCurrentUser().id,
            localDateTimeFactory(),
            name,
            color ?: DEFAULT_PROJECT_COLOR,
            isFavourite ?: false,
        )
    }

    companion object {
        private val DEFAULT_PROJECT_COLOR = Color("#666666")
    }
}
