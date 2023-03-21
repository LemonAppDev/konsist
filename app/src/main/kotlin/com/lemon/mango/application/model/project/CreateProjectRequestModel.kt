package com.lemon.mango.application.model.project

import com.lemon.mango.domain.common.model.Color

data class CreateProjectRequestModel(
    val name: String,
    val color: Color?,
    val isFavourite: Boolean?,
)
