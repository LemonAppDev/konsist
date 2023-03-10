package com.mango.domain.activity

import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class ProjectActivity(
    val userId: UserId,
    val type: ProjectActivityType,
    val projectId: ProjectId,
    val date: LocalDateTime,
)
