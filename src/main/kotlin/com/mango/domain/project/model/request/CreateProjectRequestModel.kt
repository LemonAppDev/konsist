package com.mango.domain.project.model.request

import com.mango.domain.common.model.Color

data class CreateProjectRequestModel(
    val name: String,
    val isFavourite: Boolean?,
    val color: Color?,
)
