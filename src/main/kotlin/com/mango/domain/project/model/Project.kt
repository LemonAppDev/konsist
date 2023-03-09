package com.mango.domain.project.model

import com.mango.domain.common.model.Color
import com.mango.domain.user.model.User
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class Project(
    val id: ProjectId,
    val owner: UserId,
    val creationDate: LocalDateTime,
    val name: String,
    val color: Color,
    val isFavourite: Boolean = false,
    val collaborators: List<User>? = null,
)
