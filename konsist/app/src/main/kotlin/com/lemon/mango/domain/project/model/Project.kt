package com.lemon.mango.domain.project.model

import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class Project(
    val id: ProjectId,
    val owner: UserId,
    val creationDate: LocalDateTime,
    val name: String,
    val color: Color,
    val isFavourite: Boolean = false,
)
