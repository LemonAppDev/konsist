package com.mango.business.model

import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.UserId
import java.time.LocalDateTime

data class Project(
    val id: ProjectId,
    val creator: UserId,
    val creationDate: LocalDateTime,
    val name: String,
    val collaborators: List<UserId>? = null,
    val isFavourite: Boolean = false,
    val color: Color = Color.GREY,
)
