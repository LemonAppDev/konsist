package com.lemon.mango.application.model.project

import com.lemon.mango.domain.project.model.ProjectId

data class DuplicateProjectRequestModel(
    val projectId: ProjectId,
    val name: String? = null,
)
