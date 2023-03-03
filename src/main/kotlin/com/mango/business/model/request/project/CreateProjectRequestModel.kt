package com.mango.business.model.request.project

import com.mango.business.model.value.Color
import com.mango.business.model.value.UserId

data class CreateProjectRequestModel(
    val name: String,
    val collaborators: List<UserId>? = null,
    val isFavourite: Boolean? = null,
    val color: Color? = null,
)
