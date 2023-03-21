package com.mango.application.model.project

import com.mango.domain.project.model.ProjectId

data class DuplicateProjectRequestModel(
    val projectId: ProjectId,
    val name: String? = null,
)
