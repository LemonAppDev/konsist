package com.mango.domain.activity.model

import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class ProjectActivity(
    val id: ProjectActivityId,
    val userId: UserId,
    val type: ProjectActivityType,
    val projectId: ProjectId,
    val date: LocalDateTime,
    val newValue: String?,
    val oldValue: String?,
)
