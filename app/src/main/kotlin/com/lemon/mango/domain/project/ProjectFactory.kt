package com.lemon.mango.domain.project

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class ProjectFactory(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UuidFactory,

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
