package com.mango.business.model.activity.project

import com.mango.business.model.activity.Activity
import com.mango.business.model.value.ProjectId
import java.time.LocalDateTime

sealed class ProjectActivity(val projectId: ProjectId, val date: LocalDateTime) : Activity
