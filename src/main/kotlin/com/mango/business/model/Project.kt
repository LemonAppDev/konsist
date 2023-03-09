package com.mango.business.model

import com.mango.business.model.value.Color
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.UserId
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
