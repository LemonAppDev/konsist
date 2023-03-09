package com.mango.domain.project.model.request

import com.mango.domain.common.model.Color
import com.mango.domain.user.model.UserId

data class CreateProjectRequestModel(
    val name: String,
    val collaborators: List<UserId>? = null,
    val isFavourite: Boolean? = null,
    val color: Color? = null,
)
