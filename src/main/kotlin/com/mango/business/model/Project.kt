package com.mango.business.model

import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.UserId

data class Project(
    val id: ProjectId,
    val name: String,
    val color: Color = Color.GREY,
    val collaborators: List<UserId>? = null,
    val isFavourite: Boolean = false,
)
