package com.lemon.mango.domain.activity.model

import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.user.model.UserId
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
