package com.mango.domain.activity

import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.common.UUIDFactory
import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProjectActivityFactory(
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        projectId: ProjectId,
        date: LocalDateTime,
        type: ProjectActivityType,
    ) = ProjectActivity(
        uuidFactory.createProjectActivityId(),
        userRepository.getCurrentUser().id,
        type,
        projectId,
        date,
    )
}
