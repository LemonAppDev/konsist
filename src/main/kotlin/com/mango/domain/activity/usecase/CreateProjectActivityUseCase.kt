package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.ProjectActivity
import com.mango.domain.activity.ProjectActivityType
import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CreateProjectActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        projectId: ProjectId,
        date: LocalDateTime,
    ) = ProjectActivity(userRepository.getCurrentUser().id, ProjectActivityType.CREATE, projectId, date).also {
        activityRepository.addProjectActivity(it)
    }
}
