package com.mango.application.model.project

import com.mango.domain.common.model.Color

data class CreateProjectRequestModel(
    val name: String,
    val color: Color?,
    val isFavourite: Boolean?,
)
