package com.lemon.mango.application.model.project

import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.model.ProjectId

data class UpdateProjectRequestModel(
    val projectId: ProjectId,
    val name: String,
    val color: Color,
    val isFavourite: Boolean,
)
