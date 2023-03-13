package com.mango.domain.activity

import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProjectActivityFactory(
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        projectId: ProjectId,
        date: LocalDateTime,
        type: ProjectActivityType,
    ) = ProjectActivity(
        userRepository.getCurrentUser().id,
        type,
        projectId,
        date,
    )
}
