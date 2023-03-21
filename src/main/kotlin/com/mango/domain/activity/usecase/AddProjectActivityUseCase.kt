package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AddProjectActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        projectId: ProjectId,
        type: ProjectActivityType,
        date: LocalDateTime? = null,
        newValue: String? = null,
        oldValue: String? = null,
    ) {
        val activity = ProjectActivity(
            uuidFactory.createProjectActivityId(),
            userRepository.getCurrentUser().id,
            type,
            projectId,
            date ?: localDateTimeFactory(),
            newValue,
            oldValue,
        )
        activityRepository.addProjectActivity(activity)
    }
}
