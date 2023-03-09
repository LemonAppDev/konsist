package com.mango.domain.project.activity

import com.mango.domain.activity.Activity
import com.mango.domain.project.model.ProjectId
import java.time.LocalDateTime

sealed class ProjectActivity(val projectId: ProjectId, val date: LocalDateTime) : Activity
